

const hoverInnerDiv = (querySelection)=>{
	const medUserElem = document.querySelector(querySelection);
    for(let el in medUserElem.childNodes){
    	
        if((typeof el) === 'number'){
        	console.log(el);
        	
        	if(medUserElem.childNodes(el).toString() === '[object HTMLDivElement]'){
            let hoverParent = document.createElement(medUserElem.tagName);
            hoverParent.classList.add(...medUserElem.className.split(' '));
            hoverParent.classList.add('hover');
            hoverParent.innerHTML = el;
            
            document.body.insertBefore(hoverParent, medUserElem);
         }
        }
    }
};
const functions = {
  hoverInnerDiv: (evt)=> {
    for(let el in evt.target.childNodes){
      if(evt.target.childNodes[el].toString() === '[object HTMLDivElement]'){
        const oOriginalDiv = evt.target.childNodes[el];
        let hoverParent = document.createElement(evt.target.tagName);
        hoverParent.classList.add(...evt.target.className.split(' '));
        hoverParent.classList.add('hover');
        hoverParent.insertBefore(oOriginalDiv.cloneNode(true), null);
        evt.target.parentElement.insertBefore(hoverParent, evt.target);
      }
    }
  }
}
module.exports = functions;
