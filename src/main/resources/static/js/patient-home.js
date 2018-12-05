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
