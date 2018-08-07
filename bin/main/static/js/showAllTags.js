// 8/6 11:51 PM
// rewview.html Currently showing ALL TAGS IN REPO
// Figure out how to only access tags in current reivew

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {

        const resJsonObjects = JSON.parse(xhr.response);
        console.log({resJsonObjects, xhr});

        const tagsContainer = document.querySelector('#tagsContainer');

        resJsonObjects.forEach(function(tag){
            
            const tagItem = document.createElement('div');
            tagsContainer.appendChild(tagItem);

            const tagName = document.createElement('p');
            tagName.innerText = tag.name;

            tagItem.appendChild(tagName);
            


        });
       
    }
}

xhr.open('GET', 'http://localhost:8080/tags-json', true)
xhr.send()