(() => {
    const searchInput = document.getElementById("searchInput");
    const closeChatBtn = document.getElementById("closeChatBtn");
    closeChatBtn.addEventListener("click", closeChat);

    if (!searchInput) {
        console.error("Search input doesn't exist");
        return;
    }
    const debounced = debounce(fetchSuggestions, 250);
    searchInput.addEventListener("keyup", debounced);
})()

let toUser;
let fromUser;
let suggestionCnt = 0;
let usersMap = {};

function debounce(func, delay) {
    let timeoutId;
    return function () {
        clearTimeout(timeoutId);
        timeoutId = setTimeout(() => func(), delay);
    }
}

function closeChat() {
    document.getElementById("personalChat").style.visibility = "hidden";
}

function createSuggestionLiElement(suggestionCounter, username) {
    let liElement = document.createElement('li');
    liElement.id = "suggestion" + suggestionCounter;
    liElement.textContent = username;
    liElement.addEventListener("click", function () {
        document.getElementById("searchInput").value = "";
        document.getElementById("suggestions").replaceChildren([]);
        document.getElementById("personalChat").style.visibility = "visible";
        document.getElementById("chat-with-username").textContent = username;
        toUser = username;
        fromUser = document.getElementById("greeting-p").innerHTML.substring(9);
    });
    liElement.addEventListener("mouseover", function () {
        liElement.classList.add("cadetBlueBackground");
    });
    liElement.addEventListener("mouseout", function () {
        liElement.classList.add("beigeBackground");
    })
    return liElement;
}

function fetchSuggestions() {
    let keyword = document.getElementById("searchInput").value.trim();

    let safeKeyword = encodeURIComponent(keyword);
    if (safeKeyword.length < 2) {
        document.getElementById("suggestions").innerHTML = "";
        return;
    }

    fetch(`/api/users/search?keyword=${safeKeyword}`, {method: 'GET'})
        .then(response => {
            if (!response.ok) {
                throw new Error("Response is not ok.");
            }
            return response.json();
        })
        .then(users => {
            document.getElementById("suggestions").innerHTML = "";

            users.forEach(user => {
                usersMap[user.username] = user.id;
                suggestionCnt++;

                let liElement = createSuggestionLiElement(suggestionCnt, user.username);
                document.getElementById("suggestions").appendChild(liElement);
            });
        })
        .catch(error => console.error("There is a problem with suggestions fetching.\n", error));
}