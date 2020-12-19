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

                    <create-project  v-bind:projectsArray="projectsArray" v-bind:clickedProject="'empty'" class="mx-auto"/>

                </v-row>

                <v-row v-show="componentId === 1">

                    <span v-for="(project, index) in projectsArray" :key="index" class="mx-3 my-2">
                        <project-card v-on:view-more-details="viewMoreDetails" v-bind:project="project"/>
                    </span>

                    <h1 v-if="projectsArray.length < 1" class="mx-auto text-uppercase my-6">No projects found</h1>

                </v-row>

                <v-row v-if="componentId === 2">

                    <create-project v-bind:projectsArray="projectsArray" v-bind:clickedProject="clickedProject"  v-on:deleted-project="componentId = 1" class="mx-auto" />

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
import CreateProject from '../components/CreateProject.vue';
import ProjectCard from '../components/ProjectCard.vue';
import SearchBar from '../components/SearchBar.vue';

export default {
    name: "Projectos",
    components: {
        SearchBar,
        ProjectCard,
        CreateProject,
        productName: "123",
        productMaterials: "123",
        productDimensions: "123"
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
            await this.$axios.get('/api/materiais').then(response => {
                    if (response.data)
                        response.data.forEach(resp => this.materialsArray.push(resp.tipoDeMaterial));
                }
            ).catch(error => console.log(error.message));
        },
        getProjects: async function ()
        {
            await this.$axios.get('/api/projeto').then(response => {
                this.projectsArray = response.data
            }).catch(error => console.log(error.message));
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
