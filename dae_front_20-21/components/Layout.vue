<template>
    <v-card>
    <v-card-title class="headline">
        <search-bar />
        <v-divider></v-divider>
    </v-card-title>
    <v-card-text>
        <v-row>
            <v-col v-for="i in (1,2,3)" :key=i>
                <product-card />
            </v-col>
        </v-row>
    </v-card-text>
    <v-card-actions>
        <v-spacer />
    </v-card-actions>
    </v-card>
</template>

<script>
import ProductCard from './ProductCard.vue';
import SearchBar from './SearchBar.vue';

export default {
    components: {
        SearchBar,
        ProductCard
    },
    data: () =>( {
        materialsArray: [{}],
        projectsArray: [{}]
    }),
    created: () => {
        //this.getMaterials();
        //this.getProjects();
    },
    methods: {
        getMaterials: () => {
            axios.get('/api/materiais').then(response => productsArray = response.data).catch(error => console.log(error.message));
        },
        getProjects: () => {
            axios.get('/api/projeto').then(response => projectsArray = response.data).catch(error => console.log(error.message));
        }
    }
}
</script>

<style>
</style>