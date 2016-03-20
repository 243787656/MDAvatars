function addSvgStuff(id,link) {
    var svg = document.getElementById('Layer_1');
    var svgNS = svg.namespaceURI;
    var pattern = document.createElementNS(svgNS, 'pattern');

    pattern.setAttribute('id', id);
    pattern.setAttribute('patternUnits', 'userSpaceOnUse');
    pattern.setAttribute('width', 800);
    pattern.setAttribute('height', 800);

        var image = document.createElementNS(svgNS, 'image');
        image.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href', link);
        image.setAttribute('x', -200);
        image.setAttribute('y', 50);
        image.setAttribute('width', 800);
        image.setAttribute('height', 800);

    pattern.appendChild(image);

    var defs = svg.querySelector('defs') ||
    svg.insertBefore( document.createElementNS(svgNS,'defs'), svg.firstChild);

    document.getElementById('mainbody').setAttribute('fill','url(#' + id + ')');

    return defs.appendChild(pattern);
}