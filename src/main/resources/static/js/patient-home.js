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
};

const unhidePatientStatusImages = (imageSelector, evnt) => {
  document.querySelectorAll(imageSelector).forEach((imgEl) => imgEl.classList.remove('hidden'))
};

const changeFlexDirection = (containerSelection) => {
  if (document.querySelector(containerSelection).style.flexDirection == "row") {
    document.querySelector(containerSelection).style.flexDirection = "column";
  } else {
    document.querySelector(containerSelection).style.flexDirection = "row";
  }
}

// listener present on .patientStatus to 1) swap siblings-children, 2) remove .hidden, then 3) any ".patientStatus img" items to get the following click listener:
			  // Click listener on images will 1) cancel propagation of bubble, 2) fetch POST add-status,
			  // 3) hide other three icons, (pause for fade) 4) then swap siblings-children
// need class name for all emotion indicators, and the selector for the container
const emotionIndicatorClickHandler = (containerSelector, patientId, emotionIndicatorSelector, eventObj) => {
  console.log(`In emotionIndicatorClickHandler and received emotionIndicatorSelector ${emotionIndicatorSelector} and event object: ${eventObj}`);
  swapInnerDiv(containerSelector, eventObj);
  changeFlexDirection(containerSelector);
  unhidePatientStatusImages(emotionIndicatorSelector, eventObj);
  document.querySelectorAll(emotionIndicatorSelector).forEach(
    (v, i, collection) => {
        const targetEmotionIndicator = v;
        v.addEventListener(
            'click',
            (event) => {
                event.stopPropagation();
                event.cancelBubble = true;
                const headerObj = {
                  method: "POST",
                  cache: "no-cache",
                  credentials: "same-origin",
                  headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                  }
                };
                fetch(`http://localhost:8080/add-status/${targetEmotionIndicator.getAttribute('data-emotionIndicator')}/${patientId}`, headerObj).then(
                    (response) => response.text
                ).catch((err) => console.log(`add-status POST error: ${err.message}`));
                document.querySelectorAll(emotionIndicatorSelector).forEach((value, index, list) => {
                    if(value.getAttribute('data-emotionIndicator')!==targetEmotionIndicator.getAttribute('data-emotionIndicator'))  value.classList.add('hidden');
                });
                window.setTimeout(swapInnerDiv.bind(null,'.patientStatus', event), 3000);
                changeFlexDirection(containerSelector);
    }
  );
  });
};


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
