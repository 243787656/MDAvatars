# MDAvatars

    Welcome to MDavatars you can follow the development here, i'll update this README later 
    to show you how to use and add SVG kit to this app.
    
###CREATE YOUR OWN KIT
####Part I : Illustration
Lets start ! to create your own kit you need to provide to the app your SVG file. I'm using 
Adobe Illustrator cc here
We will start from scratch with the android one :


![Android SVG](/../master/illustrator_1.png?raw=true "Android SVG")




You have many tutos on the web to show you how to make correct svg for android apps. for now 
you have to be sure to name all component of your svg path/group as below : 


![Android SVG](/../master/illustrator_2.png?raw=true "Android SVG")




After this you can save your file with #crtl + alt + S. Be sure to select SVG Basic 1.1 or Tiny 2.1
You can compare the svg code


![Android SVG](/../master/illustrator_3.png?raw=true "Android SVG")



####Part II : Java code

    
##Using Standalone SVG Manager
I've made a UIManager class to work with svgs : 
I assume you have a working html file with svg in. Check porject Assets folder for more info.
A working path : 
```xml
<path id="AK_HeadColor" fill="#00C853" d="M323.2,278.9l28.5-41.4c1.6-2.4,1.3-5.7-1-7.2c-2.3-1.6-5.4-0.7-7,1.6l-29.6,43
	c-19.5-7.7-41.2-12.1-64-12.1s-44.5,4.2-64,12.1l-29.8-43c-1.6-2.4-4.9-3.3-7-1.6c-2.3,1.6-2.6,4.9-1,7.2l28.5,41.4
	c-45.4,21-77,61.2-81.1,107.8h308.6C400.4,340.1,368.5,300,323.2,278.9z M185.6,344.2c-9.4,0-17.1-7.7-17.1-17.1s7.7-17.1,17.1-17.1
	c9.4,0,17.1,7.7,17.1,17.1S194.9,344.2,185.6,344.2z M314.6,344.2c-9.4,0-17.1-7.7-17.1-17.1s7.7-17.1,17.1-17.1s17.1,7.7,17.1,17.1
	S323.9,344.2,314.6,344.2z"></path>
```

first create your UIManager Object you need to 
```java
UIManager manager = new UImanager(webview);
```
or
```java
UIManager manager = new UImanager();
manager.attachWebView(mWebview);
```
To load a color for a single item (not under a group) you can use an int or a String eg : "#FFFFFF":
```java
public void loadColor(int color, String item);
public void loadColor(String color, String item);
```
All javascript keywords are working ('transparent','blue'...)
```java
manager.loadColor("transparent","head");
```

To load a color for an single item stroke (not under a group) you can use an int or a String :
```java
public void loadStrokeColor(int color, String item);
public void loadStrokeColor(String Color, String item);
```

To load a color for a <path> group
```java
public void loadColorforGroup(int color,String group);
public void loadColorforGroup(String color,String group);
```

To start with Java code you need to know bases of Kit implementation : 
- a custom kit extends Kit class and all methods of this class
- a custom kit is used to generate fragments (categories)
- a custom kit has 2 main Arrays wich are used for the fragment : `Categories` & `Listener`
- all item into `Categories` (except the title) MUST HAVE a `Listener` associated

great you will see what I mean

First let's create a new AndroidKit class (or whatever you want) that extends Kit:

###All is done do not forget to contact me if needed ! 
