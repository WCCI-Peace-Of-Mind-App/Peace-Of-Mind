

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
  },
  cloneNonDivChildren: (oDivParent, aNonDivChildNodes) => {
    for(let el in oDivParent.childNodes){
      if(oDivParent.childNodes.hasOwnProperty(el)){
        if(`${oDivParent.childNodes[el]}` !== '[object HTMLDivElement]'){
          if(`${oDivParent.childNodes[el]}`!='[object Text]' || oDivParent.childNodes[el].length>0){
            aNonDivChildNodes[aNonDivChildNodes.length] = oDivParent.childNodes[el].cloneNode(true);
          }
        }
      }
    }
  },
  deleteNonDivChildren: (oDivParent) => {
    let k = 0;
    while(oDivParent.childNodes.hasOwnProperty(k)){
      if(`${oDivParent.childNodes[k]}` === '[object HTMLDivElement]'){
        k++;
      } else {
        oDivParent.removeChild(oDivParent.childNodes[k]);
      }
    }
  },
  removeDivInnerHtml: (oInnerDiv) => {
    const answer = oInnerDiv.innerHTML;
    while(oInnerDiv.childNodes.hasOwnProperty(0)){
      oInnerDiv.removeChild(oInnerDiv.childNodes[0]);
    }
    return answer;
  },
  insertArrayOfNodes: (oElementToFill, aNodeFilling) => {
    for (const key in aNodeFilling) {
      if (aNodeFilling.hasOwnProperty(key))
          oElementToFill.insertBefore(aNodeFilling[key], null);
    }
  },swapInnerDiv: function(evt, selector) {
    let oContainer;
    for (const iterator in this) {
      if(this.hasOwnProperty(iterator))
          console.log(`In swapInnerDiv this is ${this} and key ${iterator} points to ${this[iterator]}`); 
    }
    oContainer = document.querySelector(selector);
    if(/*oContainer && oContainer.hasOwnProperty('childNodes') &&*/ oContainer.childNodes.length>0){
      evt.cancelBubble = true;
      const aIncumbentInnerChildren = [];
      functions.cloneNonDivChildren(evt.target, aIncumbentInnerChildren);
      functions.deleteNonDivChildren(evt.target);
      const sNewDivSiblings = functions.removeDivInnerHtml(evt.target.childNodes[0]);
      functions.insertArrayOfNodes(evt.target.childNodes[0], aIncumbentInnerChildren);
      evt.target.insertAdjacentHTML('afterbegin', sNewDivSiblings);
    }
  }

}
/*
const swapInnerDiv = (evt) => {
  let aNonDivchildNodes = [];
  let sIncumbentDivInside = '';
  let oOriginalDiv;
  for(let el in evt.target.childNodes){
    if(evt.target.childNodes.hasOwnProperty(el)){
      if(evt.target.childNodes[el].toString() === '[object HTMLDivElement]'){
        oOriginalDiv = evt.target.childNodes[el];
        sIncumbentDivInside = oOriginalDiv.innerHTML;
        for(let i in oOriginalDiv.childNodes){
          if(oOriginalDiv.childNodes.hasOwnProperty(i)) oOriginalDiv.removeChild(oOriginalDiv.childNodes[i]);
        }
      } else {
        if(`${evt.target.childNodes[el]}`!='[object Text]' || evt.target.childNodes[el].length>0){
          aNonDivchildNodes[aNonDivchildNodes.length] = evt.target.childNodes[el].cloneNode(true);
          evt.target.removeChild(evt.target.childNodes[el]);
        }
      }
    }
  }
  if(oOriginalDiv!=null){
    aNonDivchildNodes.forEach(
      (value, index, array) => {
        console.log(`IN foreach loop. value: ${value} and index: ${index}`);
        if(`${value}`=='[object Text]'){
          oOriginalDiv.insertAdjacentText('beforeend',value);
        } else {
          oOriginalDiv.insertBefore(value, null);
        }
      }
    )
    console.log(`Original Div was found and the collection of siblings iterated. The content of the original Div was ${sIncumbentDivInside}`);
    evt.target.insertAdjacentHTML('insertafter', sIncumbentDivInside);
  }
  
};
*/
module.exports = functions;
