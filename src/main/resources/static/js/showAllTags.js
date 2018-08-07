// 8/6 11:51 PM
// rewview.html Currently showing ALL TAGS IN REPO
// Figured out how to only retrieve a single review JSON object
// work on putting tag links with names

let reviewIdTag = document.querySelector('#reviewId');
let reviewId = reviewIdTag.innerText;

console.log(reviewId);

const reviewIdXhr = new XMLHttpRequest()
reviewIdXhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            jsonResponse = JSON.parse(reviewIdXhr.response);
            // x = jsonResponse.tagNames[0];
            console.log({jsonResponse, reviewIdXhr});

        const tagsContainer = document.querySelector('#tagsContainer');
        
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


    


// const xhr = new XMLHttpRequest()
// xhr.onreadystatechange = function () {
//     if (this.readyState == 4 && this.status == 200) {

//         const jsonResponse = JSON.parse(xhr.response);
//         console.log({resJsonObjects, xhr});

//         const tagsContainer = document.querySelector('#tagsContainer');

//         jsonResponse.forEach(function(review){
            // review.tagName
//             const tagItem = document.createElement('div');
//             tagsContainer.appendChild(tagItem);

//             const tagName = document.createElement('p');
//             tagName.innerText = tag.name;

//             tagItem.appendChild(tagName);
            


//         });
       
//     }
// }

// xhr.open('GET', 'http://localhost:8080/reviews-json', true)
// xhr.send()