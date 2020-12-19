<template>
    <div style="width: 600px;">

        <h2 class="text-h4 py-4 font-weight-bold text-uppercase text-center">{{title}}</h2>

        <v-text-field
            v-if="isProduct ? !isProduct : !isVariante"
            v-model="projectData.nome"
            class="mb-0"
            full-width
            solo
            :disabled="clickedProject !== 'empty'"
            label="Nome do Projeto"
            clearable
        ></v-text-field>

        <v-text-field
            v-if="isProduct"
            v-model="produto.nome"
            class="mb-0"
            full-width
            solo
            label="Nome do Produto"
            clearable
        ></v-text-field>

        <v-text-field
            v-if="isVariante"
            v-model="variante.nome"
            class="mb-0"
            full-width
            solo
            label="Nome da Variante"
            clearable
        ></v-text-field>

         <!--  -->


        <v-text-field
            v-if="isVariante"
            v-model="variante.nomeProduto"
            class="mb-0"
            full-width
            solo
            label="Nome da Nome Produto"
            clearable
        ></v-text-field>

        <v-text-field
            v-if="isVariante"
            v-model="variante.weff_p"
            class="mb-0"
            full-width
            solo
            label="Weff_p"
            clearable
        ></v-text-field>

        <v-text-field
            v-if="isVariante"
            v-model="variante.weff_n"
            class="mb-0"
            full-width
            solo
            label="Weff_n"
            clearable
        ></v-text-field>

        <v-text-field
            v-if="isVariante"
            v-model="variante.ar"
            class="mb-0"
            full-width
            solo
            label="ar"
            clearable
        ></v-text-field>

        <v-text-field
            v-if="isVariante"
            v-model="variante.sigmaC"
            class="mb-0"
            full-width
            solo
            label="sigmaC"
            clearable
        ></v-text-field>


        <v-text-field
            v-show="isProduct ? !isProduct : !isVariante"
            v-model="projectData.usernameProjetista"
            solo
            class="mb-0"
            disabled
            clearable
        ></v-text-field>

        <span v-for="structure in projectData.estruturas" :key="structure">

            <v-row>

                <v-col class="pt-0">

                    <v-text-field
                        v-model="structure.nome"
                        solo
                        class="mb-0"
                        disabled
                        clearable
                    ></v-text-field>
                    
                </v-col>

            </v-row>

        </span>


        <span v-if="clickedProject !== 'empty' &&  !isVariante">

            <v-row v-for="cliente in clickedProject.clientes" :key="cliente.nome">

                <v-col cols="10" class="py-0">

                    <v-text-field
                        v-model="cliente.username"
                        solo
                        disabled
                        class="mb-0"
                        clearable
                    ></v-text-field>

                </v-col>

                <v-col cols="2"  class="py-0">

                    <v-btn
                        class="py-6 px-5"
                        color="error"
                        block
                        @click="removeClientProject(cliente.username)"
                        >
                        <v-icon dark>
                            mdi-close
                        </v-icon>
                    </v-btn>

                </v-col>

            </v-row>

       
        <span v-if="(projectData.clientes.length == 0 || projectData.clientes.filter(client => !projectData.clientes.includes(client.username)).length > 0)">
            <v-row v-for="i in index" :key="i">

                <v-col cols="10" class="py-0">

                    <v-select
                    :items="clientNameArray.filter(client => !projectData.clientes.includes(client.username))"
                    label="Nome de Cliente"
                    solo
                    v-model="name"
                    ></v-select>

                </v-col>

                <v-col cols="2"  class="py-0">

                    <v-btn
                        class="py-6 px-5"
                        :color="i < index ? 'error' : 'primary'"
                        block
                        @click="adicionarNovoCliente(i < index)"
                        >
                        <v-icon dark>
                            {{(index > i) ? 'mdi-close' : 'mdi-account-plus'}}
                        </v-icon>
                    </v-btn>

                </v-col>

            </v-row>
        </span>

        <h4 v-else class="mx-auto text-uppercase text-center mb-4">No more existing clients that aren't already in the project.</h4>

        </span>

        <v-row>

                <v-col class="py-0" :cols="clickedProject !== 'empty' ? 9 : 12">

                    <v-btn
                    block
                    :loading="loading"
                    :disabled="loading"
                    color="success"
                    @click="!isProduct ? (isVariante ? uploadVariante() : uploadData()): postProduto()"
                    >
                    Submit
                    <template v-slot:loader>
                        <span>Submitting...</span>
                    </template>
                    </v-btn>

                </v-col>

            <v-col class="py-0" v-if="clickedProject !== 'empty'" cols="3">

                <v-btn
                color="error"
                block
                @click="deleteProject()"
                ><v-icon>mdi-delete</v-icon>
                Delete
                </v-btn>

            </v-col>

        </v-row>

    </div>
</template>

<script>
export default {
    props: ['clickedProject', 'projectsArray', 'username', 'componentId', 'title', 'clientNameArray', 'isProduct', 'isVariante'],
    data: () => ({
        index: 1,
        indexVariante: 1,
        produto: {
            nome: ''
        },
        name: '',
        projectData: {
            nome: "",
            usernameProjetista: "",
            clientes: []
        },
        emptyObject: {
            nome: "",
            usernameProjetista: "",
            clientes: []
        },
        loader: null,
        loading: false,
        clientArray: [],
        variante:
        {
            nomeProduto: '',
            nome: '',
            weff_p: '',
            weff_n: '',
            ar: '',
            sigmaC: ''
        },
        variantesArray: []
    }),
    created: function ()
    {
        if (this.clickedProject !== 'empty')
            this.projectData = this.clickedProject;

        let username = localStorage.getItem('username')
        typeof username !== undefined ? this.projectData.usernameProjetista = username : this.getUser();
    },
    methods: {
        uploadData: async function ()
        {
            if (this.name && this.clickedProject)
            {
                let tempArray = [];
                this.projectData.clientes = this.clickedProject.clientes.forEach( client => { tempArray.push({username: client.username})});
                this.projectData.clientes = tempArray;
                this.projectData.clientes.push({username: this.name});
                console.log(name);
                await this.$axios.put('/api/projeto/' + this.clickedProject.id + '/enrollclients', this.projectData.clientes).then(this.addProjectToArray()).catch(error => console.log(error.message));
            }
            else
                await this.$axios.post("/api/projeto/", this.projectData).then(response => this.addProjectToArray(response.data.id)).catch(error => console.log(error.message));

            this.$emit('back-to-homepage');
        },
        uploadVariante: async function ()
        {
            await this.$axios.post('/api/variante', this.variante).then(response => console.log(response.message)).catch(error => console.log(error.message));
        },
        deleteProject: async function ()
        {
            await this.$axios.delete('/api/projeto/delete/' + this.clickedProject.id).then( this.removeProjectFromArray(this.clickedProject.id) ).catch(error => console.log(error.message));
        },
        postProduto: async function ()
        {
            await this.$axios.post('/api/produto', this.produto).then(response => console.log(response.message)).catch(error => console.log(error.message));
        },
        removeClientProject: async function ( username )
        {
            await this.$axios.put('/api/projeto/' + this.clickedProject.id + '/unrollclient', {'username': username}).then(response => {
                var index = this.clickedProject.clientes.findIndex(client => client.username == username);
                this.clickedProject.clientes.splice(index, 1);
            } ).catch(error => console.log(error.message));
        },
        addProjectToArray: function (id)
        {
            if (this.clickedProject === "empty")
            {
                this.projectData.id = id;
                this.projectsArray.push( this.projectData );
            }
            else
            {
                var index = this.projectsArray.findIndex(project => project.id == this.projectData.id);
                this.projectsArray[index] = this.projectData;
            }
        },
        async login()
        {
            localStorage.setItem('username', this.username);
            await this.$axios.post('/api/login/token', {username: this.username, password: this.password})
            .then(response => this.setToken(response.data))
            .catch(error => console.log(error.message));
        },
        removeProjectFromArray: function (id)
        {
            var index = this.projectsArray.findIndex(project => project.id == this.projectData.id);
            this.projectsArray.splice(index, 1);
            this.$emit('deleted-project');
        },
        adicionarNovoCliente: function (increment)
        {
            increment ? this.index-- : this.index++;
        }
    },
    watch: {
      loader () {
        const l = this.loader
        this[l] = !this[l]

        //TODO success toast/button text change
        setTimeout(() => ( this[l] = false ), 3000)

        this.loader = null
      },
    },
}
</script>

<style>
</style>
