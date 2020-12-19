<template>
    <v-row class="">

        <v-col cols="6" class="mx-auto">

            <v-card flat>

            <v-card-title class="headline">

                <search-bar class="mx-0" v-on:search-name="setSearchName" v-on:search-client="setSearchClients" v-bind:componentId="componentId" v-bind:clientArray="clientArray" v-on:create-project="swapComponents"/>

            </v-card-title>

            <v-divider></v-divider>

            <v-card-text>

                <v-row class="mx-auto" v-show="componentId === 0">

                    <create-project  v-on:back-to-homepage="componentId = 1" v-bind:username="username" v-bind:projectsArray="projectsArray" v-bind:clickedProject="'empty'" class="mx-auto"/>

                </v-row>

                <v-row v-show="componentId === 1">

                    <span v-for="(project, index) in projectsArray.filter( project => project.nome.toUpperCase().includes( searchName.toUpperCase()) /*&& project.cliente.username === searchClient*/ )" :key="index" class="mx-3 my-2">

                        <project-card v-on:view-more-details="viewMoreDetails" v-bind:project="project"/>

                    </span>

                    <h1 v-if="projectsArray.length < 1" class="mx-auto text-uppercase my-6">No projects found</h1>

                </v-row>

                <v-row v-if="componentId === 2">

                    <create-project v-on:back-to-homepage="componentId = 1" v-bind:username="username" v-bind:projectsArray="projectsArray" v-bind:clickedProject="clickedProject"  v-on:deleted-project="componentId = 1" class="mx-auto" />

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
    },
    data: () =>( {
        materialsArray: [],
        projectsArray: [],
        componentId: 1,
        clickedProject: {},
        productName: "",
        productMaterials: "",
        username: '',
        searchName: '',
        searchClient: '',
        clientArray: ['Zeca']
    }),
    created()
    {
        this.getMaterials();
        this.getProjects();
        //this.getAllClients();
        let user =  localStorage.getItem('username');
        user ? this.username = user : this.getUser();
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
            await this.$axios.get('/api/projeto').then(response => this.projectsArray = response.data).catch(error => console.log(error.message));
        },
        getUser: async function()
        {
            let token = localStorage.getItem('token');
            await this.$axios.post('/api/projeto', token).then(response => this.username = response.data.sub).catch(error => console.log(error.message));
        },
        async getAllClients()
        {
            await this.$axios.get('/api/user/cliente/all').then(response => this.clientArray = response.data).catch(error => console.log(error.message));
        },
        swapComponents: function (value)
        {
            this.componentId = value;
        },
        setSearchName: function (searchName)
        {
            this.searchName = searchName;
        },
        setSearchClients: function (searchClient)
        {
            this.searchClient = searchClient;
        }, 
        viewMoreDetails: function (project)
        {
            this.componentId = 2;
            this.clickedProject = project;
        },
        tou: function (value)
        {
            console.log(value);
        }
    }
}
</script>

<style>

</style>
