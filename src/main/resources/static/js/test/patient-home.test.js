const functions = require("./patient-home");
document.body.classList.add('container');
//document.body.innerHTML = '<nav><img class="logo" src="images/logo.png" alt="logo"><h2><a href="/patient?id=12">Account</a>  </h2></nav><div class="dashboard">  <section class="medication">    <img class="medicineIcon" src="images/medicine.png" alt="medication-icon">    <h2>      <a href="/my-medications?id=12">Medications</a>    </h2>  </section>  <section class="patientStatus">    <img class="emotionsIcon" src="images/emotions.png" alt="emotion-icon">    <h2>How are you feeling?</h2>    <div class="emotionalStatus">      <img class="emotionIndicator" src="images/happy.png" alt="happy-icon">      <img class="emotionIndicator" src="images/sad.png" alt="sad-icon">      <img class="emotionIndicator" src="images/angry.png" alt="angry-icon">      <img class="emotionIndicator" src="images/confused.png" alt="confused-icon">    </div>  </section>  <section class="nonMedUser">    <img class="nonMedIcon" src="images/Caregiver.png" alt="caregiver-icon">    <h2>Your Caregiver</h2>    <div class="nonMedUserInfo">      <h3>Jenny Wilson</h3>      <p>Contact</p>      <ul>        <li>home address: 123 Builder St, Columbus, Ohio 43055</li>        <li>home phone: 614-555-4321</li>        <li>work email: 321@zyx.com</li>      </ul>    </div>  </section>  <section class="medUser">    <img class="docIcon" src="images/doctor.png" alt="doctor-icon">    <h2>Medical Care Professional</h2>    <div class="medUserInfo">      <h3>Otto Octavius</h3>      <p>Specialty: Therapist</p>      <p>Institution: Ohio State Med</p>      <p>Institution Phone: 911</p>      <p>Contact</p>      <ul>        <li>home phone: 614-777-6464</li>        <li>work email: docOc@osu.com</li>      </ul>    </div>  </section>  <section class="userInfo">    <img class="addressIcon" src="images/address.png" alt="address-icon">    <h2>Your Address</h2>    <div class="userInfo">      <p>home address: 123 Baker St, Columbus, Ohio 43081</p>    </div>  </section>  <section class="diary">    <img class="diaryIcon" src="images/diary.png" alt="diary-icon">    <h2>Your Diary</h2>    <div class="diary">      <p><em>placeholder</em></p>    </div>  </section></div><footer>  <small>© 2018 Peace Of Mind WCCI Flex Fall Columbus Final project</small></footer>';

const expectedInnerHTML =
'<nav><img class="logo" src="images/logo.png" alt="logo"><h2><a href="/patient?id=12">Account</a></h2></nav><div class="dashboard"><section class="medication">    <img class="medicineIcon" src="images/medicine.png" alt="medication-icon">    <h2>      <a href="/my-medications?id=12">Medications</a>    </h2>  </section>  <section class="patientStatus">    <img class="emotionsIcon" src="images/emotions.png" alt="emotion-icon">    <h2>How are you feeling?</h2>    <div class="emotionalStatus">      <img class="emotionIndicator" src="images/happy.png" alt="happy-icon">      <img class="emotionIndicator" src="images/sad.png" alt="sad-icon">      <img class="emotionIndicator" src="images/angry.png" alt="angry-icon">      <img class="emotionIndicator" src="images/confused.png" alt="confused-icon">    </div>  </section>  <section class="nonMedUser">    <img class="nonMedIcon" src="images/Caregiver.png" alt="caregiver-icon">    <h2>Your Caregiver</h2>    <div class="nonMedUserInfo">      <h3>Jenny Wilson</h3>      <p>Contact</p>      <ul>        <li>home address: 123 Builder St, Columbus, Ohio 43055</li>        <li>home phone: 614-555-4321</li>        <li>work email: 321@zyx.com</li>      </ul>    </div>  </section> <section class="medUser hover"><div class="medUserInfo">      <h3>Otto Octavius</h3>      <p>Specialty: Therapist</p>      <p>Institution: Ohio State Med</p>      <p>Institution Phone: 911</p>      <p>Contact</p>      <ul>        <li>home phone: 614-777-6464</li>        <li>work email: docOc@osu.com</li>      </ul>    </div> </section> <section class="medUser">    <img class="docIcon" src="images/doctor.png" alt="doctor-icon">    <h2>Medical Care Professional</h2>    <div class="medUserInfo">      <h3>Otto Octavius</h3>      <p>Specialty: Therapist</p>      <p>Institution: Ohio State Med</p>      <p>Institution Phone: 911</p>      <p>Contact</p>      <ul>        <li>home phone: 614-777-6464</li>        <li>work email: docOc@osu.com</li>      </ul>    </div>  </section>  <section class="userInfo">    <img class="addressIcon" src="images/address.png" alt="address-icon">    <h2>Your Address</h2>    <div class="userInfo">      <p>home address: 123 Baker St, Columbus, Ohio 43081</p>    </div>  </section>  <section class="diary">    <img class="diaryIcon" src="images/diary.png" alt="diary-icon">    <h2>Your Diary</h2>    <div class="diary">      <p><em>placeholder</em></p>    </div>  </section></div><footer>  <small>© 2018 Peace Of Mind WCCI Flex Fall Columbus Final project</small></footer>';
/*
test('expected body of text', 
    ()=> {
        const sQuerySelection = '.medUser';
        const oTargetDivParent = document.querySelector(sQuerySelection);
        oTargetDivParent.addEventListener(
            'click',
            functions.hoverInnerDiv.bind(oTargetDivParent)
        );
        oTargetDivParent.click();
        // expect(functions.hoverInnerDiv).toBeCalled();
        const sPattern = />{1}\s*<{1}/g;
        const sReplacement = '><';
        expect(document.body.innerHTML.replace(sPattern, sReplacement)).toBe(expectedInnerHTML.replace(sPattern, sReplacement));
    }

)*/

document.body.insertAdjacentHTML('beforeend', '<section class="nonMedUser"><img class="nonMedIcon" src="images/Caregiver.png" alt="caregiver-icon"><h2>Your Caregiver</h2><div class="nonMedUserInfo"><h3>first_name last_name</h3><p>Contact</p><ul><li >[[${patient.nonMedicalUser.contactInfo.address}]]</li><li>[[${patient.nonMedicalUser.contactInfo.phone}]]</li><li>[[${patient.nonMedicalUser.contactInfo.email}]]</li></ul></div></section>');
const sExpectedCloneNodeText = '<h2>Your Caregiver</h2>';
let aClonedElements = [];
test('Assert cloneNode deeply of second child of .nonMedUser query result is expected clone node text',
    () => {
      functions.cloneNonDivChildren(document.querySelector('.nonMedUser'), aClonedElements);
      expect(aClonedElements[1].outerHTML.concat('')).toBe(sExpectedCloneNodeText);
    }
);
const sExpectedPrunedInnerHtml = '<div class="nonMedUserInfo"><h3>first_name last_name</h3><p>Contact</p><ul><li>[[${patient.nonMedicalUser.contactInfo.address}]]</li><li>[[${patient.nonMedicalUser.contactInfo.phone}]]</li><li>[[${patient.nonMedicalUser.contactInfo.email}]]</li></ul></div>';
test('Assert deleteNonDivChildren will result in expected innerHTML',
    () => {
      let oParentDiv = document.querySelector('.nonMedUser');
      functions.deleteNonDivChildren(oParentDiv);
      expect(oParentDiv.innerHTML.concat('')).toBe(sExpectedPrunedInnerHtml);
    }
);
let oTestDiv = document.createElement('div');
oTestDiv.classList.add('nonMedUserInfo');
const sExpectedInnerDivHtml = '<h3>first_name last_name</h3><p>Contact</p><ul><li>[[${patient.nonMedicalUser.contactInfo.address}]]</li><li>[[${patient.nonMedicalUser.contactInfo.phone}]]</li><li>[[${patient.nonMedicalUser.contactInfo.email}]]</li></ul>';
oTestDiv.innerHTML = sExpectedInnerDivHtml;
const sEmptyNonMedUserInfo = '<div class="nonMedUserInfo"></div>';
test(
    'Assert removeDivInnerHtml returns expected content and the remaining outerHTML is as expected',
    () => {
      expect(functions.removeDivInnerHtml(oTestDiv)).toBe(sExpectedInnerDivHtml);
      expect(oTestDiv.outerHTML.concat('')).toBe(sEmptyNonMedUserInfo);
    }
)
let oEmptyNonMedUserInfo = document.createElement('div');
oEmptyNonMedUserInfo.classList.add('nonMedUserInfo');
const sFilledNonMedUserInfo = '<div class="nonMedUserInfo"><img class="nonMedIcon" src="images/Caregiver.png" alt="caregiver-icon"><h2>Your Caregiver</h2></div>';
test(
  'Assert insertArrayOfNodes returns Filled Non-Med User Info',
  () => {
    functions.insertArrayOfNodes(oEmptyNonMedUserInfo, aClonedElements);
    expect(oEmptyNonMedUserInfo.outerHTML.concat('')).toBe(sFilledNonMedUserInfo);
  }
);
const oSectionToAddNonMedSiblings = document.createElement('section');
oSectionToAddNonMedSiblings.classList.add('nonMedUser');
oSectionToAddNonMedSiblings.innerHTML = sFilledNonMedUserInfo;
const sExpectedFinalHtml = '<section class="nonMedUser"><h3>first_name last_name</h3><p>Contact</p><ul><li>[[${patient.nonMedicalUser.contactInfo.address}]]</li><li>[[${patient.nonMedicalUser.contactInfo.phone}]]</li><li>[[${patient.nonMedicalUser.contactInfo.email}]]</li></ul><div class="nonMedUserInfo"><img class="nonMedIcon" src="images/Caregiver.png" alt="caregiver-icon"><h2>Your Caregiver</h2></div></section>';
test(
  'Assert insertAdjacentHTML afterbegin sExpectedInnerDivHtml works',
  () => {
    oSectionToAddNonMedSiblings.insertAdjacentHTML('afterbegin', sExpectedInnerDivHtml);
    expect(oSectionToAddNonMedSiblings.outerHTML.concat('')).toBe(sExpectedFinalHtml);
  }
)