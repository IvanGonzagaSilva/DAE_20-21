<template>
    <v-row>
        <v-col cols="1" class="mr-4">
            <v-btn
            class="m-2 py-6 mr-6"
            color="primary"
            @click="createProject()"
            >
            <v-icon dark>
                mdi-{{hideClass ? "chevron-left" : "plus"}}
            </v-icon>
            </v-btn>
        </v-col>
        <v-col >
            <v-text-field
            label="Solo"
            placeholder="Nome"
            solo
            v-model="productName"
          ></v-text-field>
        </v-col>
        <v-col>
            <v-select
            :items="materials"
            label="Materiais"
            solo
            v-model="productMaterials"
            ></v-select>
        </v-col>
        <v-col>
            <v-select
            :items="dimensions"
            label="Dimensões"
            solo
            v-model="productDimensions"
            ></v-select>
        </v-col>
        
        <v-col>
            <p v-show="!resetFilterHidden" class="text-uppercase text-body-1 reset-button font-weight-bold" color="blue" @click="resetFilters()">
                Reset Filters
            </p>
        </v-col>
    </v-row>
</template>

<script>
export default {
    data: () => ({
      materials: ['Perfil', 'Chapa', 'Ferro', 'Plasticina'],
      dimensions: ['200.00x1.80', '90.00x0.20', '50.00x1.00', '30.30x20.70'],
      productName: "",
      productMaterials: "",
      productDimensions: "",
      hideClass: false
    }),
    methods: {
        resetFilters: function () {
            if (typeof this.productName != undefined)
                this.productName = "";
            if (typeof this.productMaterials != undefined)
                this.productMaterials = "";
            if (typeof this.productDimensions != undefined)
                this.productDimensions = "";
        },
        createProject: function () {
            this.hideClass = !this.hideClass;
            this.$emit('create-project');
        }
    },
    computed: {
        resetFilterHidden: function () {
            return !(this.productName.length > 0 || this.productMaterials.length > 0 ||  this.productDimensions.length > 0);
        }
    }
}
</script>

<style scoped>
    .reset-button
    {
        cursor: pointer;
        padding: 12px;
        color: rgb(8, 135, 177);
    }
    .reset-button:hover
    {
        color: rgb(15, 195, 255);
    }
</style>
/* Como Projetista, eu quero criar, consultar/listar/procurar/filtrar, atualizar e remover
projetos de estruturas metálicas, para que possa manter atualizada a lista dos meus
projetos. Um projeto contém, entre outros, um nome, um cliente (nome, pessoa de
contacto, morada, email), uma lista de ficheiros (protótipos 3D da estrutura, dimensões,
etc.) e uma lista de estruturas (por exemplo, um projeto de um pavilhão industrial pode
incluir a estrutura da cobertura e a estrutura da fachada); */