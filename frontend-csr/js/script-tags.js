let useGraphQlTags = () => window.location.search.includes("graphql");

let fetchTags = function () {
    if (useGraphQlTags()) fetchTagsGraphQl();
    else fetchTagsRest();
}

let fetchTagsRest = function () {
    fetch("http://localhost:8080/api/tag")
        .then(response => response.json())
        .then(json => buildTagsList(json))
}

let fetchTagsGraphQl = async () => {
    let query = `{
            tags {
                id
                name
            }
        }`

    let response = await fetch("http://localhost:8080/graphql", {
        method: "post",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        body: JSON.stringify({ query }),
    });

    let json = await response.json();

    buildTagsList(json.data.tags);
}

let buildTagsList = function (tags) {
    let tagId = document.querySelector("#tag-list");
    tagId.innerHTML = "";
    for (let tag of tags) {
        tagId.innerHTML +=
            `
            <button class="filter-button" >${tag.name}</button>
        `
    }
}

fetchTags();