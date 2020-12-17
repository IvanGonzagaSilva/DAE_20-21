<template>
    <v-form>

        <v-row>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.nome"
                    class="mb-0"
                    solo
                    label="Nome do Projeto"
                    clearable
                ></v-text-field>

            </v-col>

        </v-row>

        <v-divider class="pb-2"></v-divider>

        <span class="text-h6 text-uppercase">Informação do Cliente</span>

        <v-row>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.cliente.nome"
                    solo
                    label="Nome da Empresa"
                    clearable
                ></v-text-field>

            </v-col>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.cliente.morada"
                    solo
                    label="Morada da Empresa"
                    clearable
                ></v-text-field>

            </v-col>

        </v-row>
        <v-row>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.cliente.email"
                    solo
                    label="Email da Empresa"
                    clearable
                ></v-text-field>

            </v-col>

        </v-row>

        <v-row>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.cliente.pc.nome"
                    solo
                    label="Nome do Cliente"
                    clearable
                ></v-text-field>

            </v-col>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.cliente.pc.username"
                    solo
                    label="Username do Cliente"
                    clearable
                ></v-text-field>

            </v-col>

        </v-row>

        <v-row>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.cliente.pc.contactoTelefonico"
                    solo
                    label="Contacto Telefónico do Cliente"
                    clearable
                ></v-text-field>

            </v-col>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.cliente.pc.email"
                    solo
                    label="Email do Cliente"
                    clearable
                ></v-text-field>

            </v-col>

        </v-row>

        <v-divider class="pb-6"></v-divider>

        <span class="text-h6 text-uppercase">Estruturas</span>

        <v-row>

            <v-col cols="10" class="pb-0">

                <v-text-field
                    v-model="projectData.estruturas"
                    class="mb-0"
                    solo
                    label="Lista de Estruturas"
                    clearable
                ></v-text-field>

            </v-col>
            
            <v-col cols="1">

                <v-btn
                    class="py-6 px-5"
                    color="primary"
                    small
                    @click="newStructure()"
                    >
                    <v-icon dark>
                        mdi-plus
                    </v-icon>
                </v-btn>
            
            </v-col>

        </v-row>

        <v-btn
        block
        :loading="loading"
        :disabled="loading"
        color="success"
        @click="uploadData()"
        >
        Submit
        <template v-slot:loader>
            <span>Submitting...</span>
        </template>
        </v-btn>
    </v-form>
</template>

<script>
export default {
    props: ['clickedProject'],
    data: () => ({
        projectData: {
            nome: "",
            cliente: {
                nome: "",
                pc: {
                    contactoTelefonico: "",
                    email: "",
                    nome: "",
                    username: ""
                },
                morada: "",
                email: ""
            },
            estruturas: []
        },
        emptyObject: {
            nome: "",
            cliente: {
                nome: "",
                pc: {
                    contactoTelefonico: "",
                    email: "",
                    nome: "",
                    username: ""
                },
                morada: "",
                email: ""
            },
            estruturas: []
        },
        loader: null,
        loading: false
    }),
    created: function ()
    {
        if (this.clickedProject !== 'empty')
            this.projectData = this.clickedProject;
    },
    methods: {
        newStructure: function() {
            console.log("Add structures");
        },
        uploadData: async function() {
            this.loader = 'loading';
            if (this.clickedProject !== 'empty')
                this.projectData = this.clickedProject;
            await this.$axios.post("http://localhost:8080/projetodae/api/projeto", this.projectData).then(response => alert(response.message)).catch(error => console.log(error.message));
        },
        deleteProject: async function ()
        {
            if (confirm('Are you sure you want to delete this project?') )
                await this.$axios.delete('http://localhost:8080/projetodae/api/projeto', this.project.id).then(response => console.log(response)).catch(error => console.log(error.message));
        }
    },
    watch: {
      loader () {
        const l = this.loader
        this[l] = !this[l]

        //TODO success toast/button text change
        setTimeout(() => ( this[l] = false, this.projectData = this.emptyObject ), 3000)

        this.loader = null
      },
    },
}
</script>

<style>
</style>