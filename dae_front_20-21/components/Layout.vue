<template>
    <v-row>
        <v-col cols="6" class="mx-auto">

            <v-card flat>
                
            <v-card-title class="headline">

                <search-bar class="mx-0" v-bind:componentId="componentId" v-bind:materialsArray="materialsArray" v-on:create-project="swapComponents"/>

                <v-divider></v-divider>

            </v-card-title>

            <v-card-text>

                <v-row class="mx-auto" v-show="componentId === 0">

                    <create-project v-bind:clickedProject="'empty'" class="mx-auto"/>

                </v-row>

                <v-row v-show="componentId === 1">
                    
                    <span v-for="(project, index) in projectsArray" :key="index" class="mx-3 my-2">

                        <project-card v-on:view-more-details="viewMoreDetails" v-bind:project="project"/>

                    </span>

                </v-row>

                <v-row v-if="componentId === 2">

                    <create-project v-bind:clickedProject="clickedProject"  class="mx-auto" />

                </v-row>

            </v-card-text>

            <v-card-actions>

                <v-spacer />

            </v-card-actions>

            </v-card>

        </v-col>
        
    </v-row>
</template>

<script>
import CreateProject from './CreateProject.vue';
import LoginForm from './LoginForm.vue';
import ProjectCard from './ProjectCard.vue';
import SearchBar from './SearchBar.vue';

export default {
    components: {
        SearchBar,
        ProjectCard,
        CreateProject,
        LoginForm
    },
    data: () =>( {
        materialsArray: [],
        projectsArray: [],
        componentId: 1,
        clickedProject: {}
    }),
    created()
    {
        this.getMaterials();
        this.getProjects();
    },
    methods: {
        getMaterials: async function ()
        {
            await this.$axios.get('http://localhost:8080/projetodae/api/materiais/').then(response => {
                    if (response.data)
                        response.data.forEach(resp => this.materialsArray.push(resp.tipoDeMaterial));
                } 
            ).catch(error => console.log(error.message));
        },
        getProjects: async function ()
        {
            await this.$axios.get('http://localhost:8080/projetodae/api/projeto').then(response => this.projectsArray = response.data).catch(error => console.log(error.message));
        },
        swapComponents: function (value)
        {
            this.componentId = value;
        },
        viewMoreDetails: function (project)
        {
            this.componentId = 2;
            this.clickedProject = project;
        }
    }
}
</script>

<style>

</style>