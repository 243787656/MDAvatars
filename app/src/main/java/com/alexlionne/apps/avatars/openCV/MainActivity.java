package com.alexlionne.apps.avatars.openCV;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.alexlionne.apps.avatars.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by root on 25/03/16.
 */
public class MainActivity extends Activity {
    static {
        System.loadLibrary("opencv_java3");
    }

    private static final String TAG = "Kate : ";
    BaseLoaderCallback mOpenCVCallBack;
    Mat mRgba;
    ImageView imge1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!OpenCVLoader.initDebug()) {
            Log.e(this.getClass().getSimpleName(), "  OpenCVLoader.initDebug(), not working.");
        } else {
            Log.d(this.getClass().getSimpleName(), "  OpenCVLoader.initDebug(), working.");
        }
    }


    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mRgba = new Mat();
                    load_AND_display();
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    public void load_AND_display() {

        //getting ImageView
        imge1 = (ImageView) findViewById(R.id.imageView);

        try {
            //Loading Image to Mat object
            mRgba = Utils.loadResource(this, R.drawable.matias, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            ColorBlobDetector.process(mRgba);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // create a bitmap:
        Bitmap bm = Bitmap.createBitmap(mRgba.cols(), mRgba.rows(), Bitmap.Config.ARGB_8888);

        // convert Mat image to bitmap::
        Utils.matToBitmap(mRgba, bm);
        //setting the image
        imge1.setImageBitmap(bm);

    }

    @Override
    public void onResume() {
        super.onResume();
        mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        //OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);


    }
}
    class ColorBlobDetector {
        // Lower and Upper bounds for range checking in HSV color space
        private static Scalar mLowerBound = new Scalar(0);
        private static Scalar mUpperBound = new Scalar(0);
        // Minimum contour area in percent for contours filtering
        private static double mMinContourArea = 0.1;
        // Color radius for range checking in HSV color space
        private static Scalar mColorRadius = new Scalar(25,50,50,0);
        private static Mat mSpectrum = new Mat();
        private static List<MatOfPoint> mContours = new ArrayList<MatOfPoint>();

        // Cache
        static Mat mPyrDownMat = new Mat();
        static Mat mHsvMat = new Mat();
        static Mat mMask = new Mat();
        static Mat mDilatedMask = new Mat();
        static Mat mHierarchy = new Mat();

        public static void setColorRadius(Scalar radius) {
            mColorRadius = radius;
        }

        public static void setHsvColor(Scalar hsvColor) {
            double minH = (hsvColor.val[0] >= mColorRadius.val[0]) ? hsvColor.val[0]-mColorRadius.val[0] : 0;
            double maxH = (hsvColor.val[0]+mColorRadius.val[0] <= 255) ? hsvColor.val[0]+mColorRadius.val[0] : 255;

            mLowerBound.val[0] = minH;
            mUpperBound.val[0] = maxH;

            mLowerBound.val[1] = hsvColor.val[1] - mColorRadius.val[1];
            mUpperBound.val[1] = hsvColor.val[1] + mColorRadius.val[1];

            mLowerBound.val[2] = hsvColor.val[2] - mColorRadius.val[2];
            mUpperBound.val[2] = hsvColor.val[2] + mColorRadius.val[2];

            mLowerBound.val[3] = 0;
            mUpperBound.val[3] = 255;

            Mat spectrumHsv = new Mat(1, (int)(maxH-minH), CvType.CV_8UC3);

            for (int j = 0; j < maxH-minH; j++) {
                byte[] tmp = {(byte)(minH+j), (byte)255, (byte)255};
                spectrumHsv.put(0, j, tmp);
            }

            Imgproc.cvtColor(spectrumHsv, mSpectrum, Imgproc.COLOR_HSV2RGB_FULL, 4);
        }

        public Mat getSpectrum() {
            return mSpectrum;
        }

        public void setMinContourArea(double area) {
            mMinContourArea = area;
        }

        public static void process(Mat rgbaImage) {
            Imgproc.pyrDown(rgbaImage, mPyrDownMat);
            Imgproc.pyrDown(mPyrDownMat, mPyrDownMat);

            Imgproc.cvtColor(mPyrDownMat, mHsvMat, Imgproc.COLOR_RGB2HSV_FULL);

            Core.inRange(mHsvMat, mLowerBound, mUpperBound, mMask);
            Imgproc.dilate(mMask, mDilatedMask, new Mat());

            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

            Imgproc.findContours(mDilatedMask, contours, mHierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            // Find max contour area
            double maxArea = 0;
            Iterator<MatOfPoint> each = contours.iterator();
            while (each.hasNext()) {
                MatOfPoint wrapper = each.next();
                double area = Imgproc.contourArea(wrapper);
                if (area > maxArea)
                    maxArea = area;
            }

            // Filter contours by area and resize to fit the original image size
            mContours.clear();
            each = contours.iterator();
            while (each.hasNext()) {
                MatOfPoint contour = each.next();
                if (Imgproc.contourArea(contour) > mMinContourArea*maxArea) {
                    Core.multiply(contour, new Scalar(4,4), contour);
                    mContours.add(contour);
                }
            }
        }

        public List<MatOfPoint> getContours() {
            return mContours;
        }
    }
