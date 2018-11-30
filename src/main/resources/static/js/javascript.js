function expandText(div) {
  var className = div.getAttribute("class");
  if(className==="collapsed-par") {
    div.className = "expanded-par";
  }
  else{
    div.className = "collapsed-par";
  }
}
