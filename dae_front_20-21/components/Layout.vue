<template>
    <v-card flat>

    <v-card-title class="headline">

        <search-bar class="mx-0" v-on:create-project="createProject"/>

        <v-divider></v-divider>

    </v-card-title>

    <v-card-text>

        <v-row class="mx-auto" v-bind:class="{ 'd-none': hideClass }">

            <product-card class="ma-3"/>
            <product-card class="ma-3"/>
            <product-card class="ma-3"/>
            <product-card class="ma-3"/>

        </v-row>

        <v-row :class="{ 'd-none': !hideClass }">
            
            <create-project class="mx-auto"/>

        </v-row>

    </v-card-text>

    <v-card-actions>

        <v-spacer />

    </v-card-actions>

    </v-card>
</template>

<script>
import CreateProject from './CreateProject.vue';
import ProductCard from './ProductCard.vue';
import SearchBar from './SearchBar.vue';

export default {
    components: {
        SearchBar,
        ProductCard,
        CreateProject
    },
    data: () =>( {
        materialsArray: [{}],
        projectsArray: [{}],
        hideClass: false
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
        },
        createProject: function () {
            //Swap between both components through SearchBar's event..
            this.hideClass = !this.hideClass;
        }
    }
}
</script>

<style>

</style>