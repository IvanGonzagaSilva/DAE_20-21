<template>
    <v-form>

        <v-row>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.projectName"
                    class="mb-0"
                    solo
                    label="Project Name"
                    clearable
                ></v-text-field>

            </v-col>

        </v-row>

        <v-divider class="pb-2"></v-divider>

        <span class="text-h6 text-uppercase">Client Information</span>

        <v-row>

            <v-col>

                <v-text-field
                    v-model="projectData.client.name"
                    solo
                    label="Name"
                    clearable
                ></v-text-field>

            </v-col>

            <v-col>

                <v-text-field
                    v-model="projectData.client.contact"
                    solo
                    label="Contact Phone"
                    clearable
                ></v-text-field>

            </v-col>

        </v-row>
        <v-row>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.client.email"
                    solo
                    label="Email"
                    clearable
                ></v-text-field>

            </v-col>

            <v-col class="pb-0">

                <v-text-field
                    v-model="projectData.client.address"
                    solo
                    label="Address"
                    clearable
                ></v-text-field>

            </v-col>

        </v-row>

        <v-divider class="pb-6"></v-divider>

        <span class="text-h6 text-uppercase">Structures</span>

        <v-row>

            <v-col cols="10" class="pb-0">

                <v-text-field
                    v-model="projectData.structures"
                    class="mb-0"
                    solo
                    label="Structures List"
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
/* Como Projetista, eu quero criar, consultar/listar/procurar/filtrar, atualizar e remover
projetos de estruturas metálicas, para que possa manter atualizada a lista dos meus
projetos. Um projeto contém, entre outros, um nome, um cliente (nome, pessoa de
contacto, morada, email), uma lista de ficheiros (protótipos 3D da estrutura, dimensões,
etc.) e uma lista de estruturas (por exemplo, um projeto de um pavilhão industrial pode
incluir a estrutura da cobertura e a estrutura da fachada); */

export default {
    data: () => ({
        projectData: {
            projectName: "",
            client: {
                name: "",
                contact: "",
                address: "",
                email: ""
            },
            files: {
                prototypes: "",
                dimensions: ""
            },
            structures: []
        },
        loader: null,
        loading: false
    }),
    methods: {
        newStructure: function() {
            console.log("Add structures");
        },
        uploadData: function() {
            this.loader = 'loading';
            axios.post("/api/materiais", projectData).then(response => console.log(response)).catch(error => console.log(error.message));
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