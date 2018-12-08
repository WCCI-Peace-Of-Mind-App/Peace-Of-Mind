const hoverInnerDiv = (evt)=> {
  for(let el in evt.target.childNodes){
    if(evt.target.childNodes[el].toString() === '[object HTMLDivElement]'){
      const oOriginalDiv = evt.target.childNodes[el];
      let hoverParent = document.createElement(evt.target.tagName);
      hoverParent.classList.add(...evt.target.className.split(' '));
      hoverParent.classList.add('hover');
      hoverParent.insertBefore(oOriginalDiv.cloneNode(true), null);
      hoverParent.addEventListener('click', (event)=> {
        event.target.parentElement.removeChild(event.target);
      } )
      document.body.insertBefore(hoverParent, document.querySelector('.dashboard'));
    }
  }
};

const cloneNonDivChildren = (oDivParent, aNonDivChildNodes) => {
  for(const el in oDivParent.childNodes){
    if(oDivParent.childNodes.hasOwnProperty(el)){
      if(`${oDivParent.childNodes[el]}` !== '[object HTMLDivElement]'){
        if(`${oDivParent.childNodes[el]}`!='[object Text]' || oDivParent.childNodes[el].length>0){
          aNonDivChildNodes[aNonDivChildNodes.length] = oDivParent.childNodes[el].cloneNode(true);
        }
      }
    }
  }
};
const deleteNonDivChildren = (oDivParent) => {
  let k = 0;
  while(oDivParent.childNodes.hasOwnProperty(k)){
    if(`${oDivParent.childNodes[k]}` === '[object HTMLDivElement]'){
      k++;
    } else {
      oDivParent.removeChild(oDivParent.childNodes[k]);
    }
  }
};
const removeDivInnerHtml = (oInnerDiv) => {
  const answer = oInnerDiv.innerHTML;
  while(oInnerDiv.childNodes.hasOwnProperty(0)){
    oInnerDiv.removeChild(oInnerDiv.childNodes[0]);
  }
  return answer;
};
const insertArrayOfNodes = (oElementToFill, aNodeFilling) => {
  for (const key in aNodeFilling) {
    if (aNodeFilling.hasOwnProperty(key))
        oElementToFill.insertBefore(aNodeFilling[key], null);
  }
};

const swapInnerDiv = (selector, evt) => {
  let oContainer;
  oContainer = document.querySelector(selector);
  if(oContainer && oContainer.childNodes && oContainer.childNodes.length>0){
    evt.cancelBubble = true;
    const aIncumbentInnerChildren = [];
    cloneNonDivChildren(oContainer, aIncumbentInnerChildren);
    deleteNonDivChildren(oContainer);
    const sNewDivSiblings = removeDivInnerHtml(oContainer.childNodes[0]);
    insertArrayOfNodes(oContainer.childNodes[0], aIncumbentInnerChildren);
    oContainer.insertAdjacentHTML('afterbegin', sNewDivSiblings);
  }
}
// const functions = {
// hoverInnerDiv: (this)=> {
// for(let el in this.childNodes){
// if(el.toString() === '[object HTMLDivElement]'){
// let hoverParent = document.createElement(this.tagName);
// hoverParent.classList.add(...this.className.split(' '));
// hoverParent.classList.add('hover');
// hoverParent.innerHTML = el;
// document.body.insertBefore(hoverParent, this);
// }
// }
// }
// }
// module.exports = functions;
