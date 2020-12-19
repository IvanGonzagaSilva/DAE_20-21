<template>
    <div style="width: 600px;">
        <v-row>

            <v-col cols="12" class="py-0">

                <v-select
                :items="produtosArray"
                label="Nome do produto"
                item-text="nome"
                solo
                v-model="produto"
                ></v-select>

            </v-col>

            <v-col cols="2"  class="py-0">

            </v-col>

        </v-row>

        <v-row v-for="i in indexVariante" :key="i">

            <v-col cols="10" class="py-0">

                <v-select
                :items="variantesArray"
                label="Nome da Variante"
                item-text="codigo"
                solo
                v-model="variante"
                ></v-select>

            </v-col>

            <v-col cols="2"  class="py-0">

                <v-btn
                    class="py-6 px-5"
                    :color="i < indexVariante ? 'error' : 'primary'"
                    block
                    @click="adicionarNovaVariante(i < indexVariante)"
                    >
                    <v-icon dark>
                        {{(indexVariante > i) ? 'mdi-close' : 'mdi-plus'}}
                    </v-icon>
                </v-btn>

            </v-col>

        </v-row>

        <v-row>
            <v-btn
            block
            color="success"
            @click="putProduto()"
            >
            Submit
            <template v-slot:loader>
                <span>Submitting...</span>
            </template>
            </v-btn>
        </v-row>
    </div>
</template>

<script>
export default {
    props: [''],
    data: () => ({
        variantesArray: [],
        indexVariante: 1,
        indexProduto: 1,
        produtosArray: [],
        variante: '',
        produto: ''
        }),
    created () {
        this.getProducts();
        this.getVariantes();
    },
    methods:{
        getVariantes: async function ()
        {
            this.$axios.get('/api/variante').then(response => this.variantesArray = response.data).catch(error => console.log(error.message));
        },
        getProducts: async function ()
        {
            this.$axios.get('/api/produto').then(response => this.produtosArray = response.data).catch(error => console.log(error.message));
        },
        putProduto: async function ()
        {
            this.$axios.put('/api/produto/' + this.produto + '/add/variante/' + this.variante).then(response => this.produtosArray = response.data).catch(error => console.log(error.message));
        },
        adicionarNovaVariante: function (increment)
        {
            increment ? this.indexVariante-- : this.indexVariante++;
        }
    }
}
</script>

<style scoped>

</style>