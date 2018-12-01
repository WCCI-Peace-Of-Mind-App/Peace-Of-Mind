const patientId = document.querySelector('.patient-id').value;
const submitStatus = document.querySelector('.submit-status')
const emotionalStatus = document.querySelector('.emotionalStatus')

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function() {
    if(xhr.readyState === 4 && xhr.status === 200) {
        const res = xhr.responseText
        updateStatus.innerHTML = res
    }
}

submitStatus.addEventListener('click', function() {
    let checkedStatus = document.querySelector('input[name="currentStatus"]:checked').value;
    postStatus(checkedStatus, patientId)
})

function postStatus(currentStatus, patientId) {
    xhr.open('POST', '/add-status/' + currentStatus + '/' + patientId, true)
    xhr.send();
}
