// 8/6 11:51 PM
// rewview.html Currently showing ALL TAGS IN REPO
// Figured out how to only retrieve a single review JSON object
// work on putting tag links with names

let reviewIdTag = document.querySelector('#reviewId');
const tagsContainer = document.querySelector('#tagsContainer');
let reviewId = reviewIdTag.innerText;
showAllTags();

let removeAllTags = function(){
    while (tagsContainer.firstChild) {
        tagsContainer.removeChild(tagsContainer.firstChild);
    }
}

function showAllTags(){
    
    const reviewIdXhr = new XMLHttpRequest()
    reviewIdXhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            removeAllTags();
            jsonResponse = JSON.parse(reviewIdXhr.response);
            // x = jsonResponse.tagNames[0];
            console.log({jsonResponse, reviewIdXhr});
 
        
        // for (i = 0; i < jsonResponse.tagNames.length; i++) {
            for (i=0; i < jsonResponse.tags.length; i++){
                let tagJson = jsonResponse.tags[i];

                const tagContainer = document.createElement('div');
                tagContainer.setAttribute("class", "tagContainer")

                let tagLink = document.createElement('a');
                tagLink.setAttribute("href", tagJson.tagUrl)
                
                const tagNameText = document.createElement('p');
                tagNameText.innerText = tagJson.name;
                
                tagLink.appendChild(tagNameText);
                tagContainer.appendChild(tagLink);
                tagsContainer.appendChild(tagContainer);
            }
       
       }
       
    }
    reviewIdXhr.open('GET', `http://localhost:8080/reviews-json/${reviewId}`);
    reviewIdXhr.send()

}

/* Add Tags Button */

const addTagButton = document.querySelector('#addTagButton'); 


addTagButton.addEventListener('click', function(){
    const nameInput = prompt('Type the name of the Tag for this Review');
    
    const addTagXhr = new XMLHttpRequest()

    addTagXhr.onreadystatechange = function(){
        if (this.readyState == 4 && this.status == 200) {
            showAllTags();
            console.log(addTagXhr.response);
        }
    }
    
    
    addTagXhr.open('POST', `http://localhost:8080/reviews-json/${reviewId}/add-tag?name=${nameInput}` )
    addTagXhr.send();
    

})
    













    
