
document.addEventListener('readystatechange',(evt) => {
  if(evt.target.readyState === 'complete'){

	  const collapsedElems = document.querySelectorAll('.collapsed-par')
    for(let i in collapsedElems){
    	if(collapsedElems.hasOwnProperty(i))
    		collapsedElems[i].addEventListener('click',expandText.bind(collapsedElems[i]));

    }
  }
})


function expandText() {

	const collapsed = "collapsed-par";
	const expanded = "expanded-par";

  if(this.classList.contains(collapsed)) {
    this.classList.replace(collapsed,expanded);
  }
  else{
	  this.classList.replace(expanded,collapsed);
  }
}
