const hoverInnerDiv = ()=> {
    for(let el in this.childNodes){
      if(el.toString() === '[object HTMLDivElement]'){
        let hoverParent = document.createElement(this.tagName);
        hoverParent.classList.add(...this.className.split(' '));
        hoverParent.classList.add('hover');
        hoverParent.innerHTML = el;
        document.body.insertBefore(hoverParent, this);
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
