import { ref } from "vue";

export default function tagService() {
    const tags = ref([]);


    const fetchTags = async () => {
        let response = await fetch("http://localhost:8080/api/tag");
        tags.value = await response.json();
    };

    return {
        tags,
        fetchTags
    }

}