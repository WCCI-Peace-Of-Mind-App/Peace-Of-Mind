

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
