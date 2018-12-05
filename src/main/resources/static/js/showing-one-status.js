const patientStatus = document.querySelector(".patientStatus");
const patientId = document.querySelector(".id").value;
const showThreeButton = document.querySelector(".show-three");

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function() {
    if(xhr.readyState === 4 && xhr.status === 200) {
        const res = xhr.responseText;
        patientStatus.innerHTML = res;
    }
}

function postThreeStatuses(patientId) {
    xhr.open('GET', '/status-history-three/' + patientId, true);
    xhr.send();
}

showThreeButton.addEventListener('click', function() {
    postThreeStatuses(patientId);
})

