<template>
    <v-row>
        <v-col class="pl-0">
            <v-btn
            class="m-2 py-6"
            color="primary"
            @click="swapComponent()"
            >
            <v-icon dark class="pr-2">
                mdi-{{componentId != 1 ? "chevron-left": "folder-plus"}}
            </v-icon>
                {{componentId == 0 ? "Voltar Página Inicial" : (componentId == 2 ? "Voltar Página Inicial" :  "Criar Novo Projeto")}}
            </v-btn>
        </v-col>

         <v-col >
            <v-text-field
            label="Solo"
            placeholder="Nome"
            solo
            :disabled="componentId !== 1"
            v-model="searchName"
            @input="emitEvent"
          ></v-text-field>
        </v-col>

        <v-col>
            <v-select
            :items="clientArray"
            label="Clientes"
            solo
            :disabled="componentId !== 1"
            v-model="searchClient"
            @input="emitEvent"
            ></v-select>
        </v-col>

        <v-col cols="2" class="px-0">
            <p v-show="!resetFilterHidden" class="text-uppercase text-body-1 reset-button font-weight-bold" color="blue" @click="resetFilters()">
                Reset Filters
            </p>
        </v-col>

    </v-row>
</template>

<script>
export default {
    props: ['clientArray', 'componentId'],
    data: () => ({
      searchClient: "",
      searchName: "",
    }),
    methods: {
        resetFilters: function () {
            if (typeof this.searchName != undefined)
            {
                this.$emit('search-name', '');
                this.searchName = "";
            }
            if (typeof this.searchClient != undefined)
            {
                this.searchClient = "";
                this.$emit('search-client', ''); 
            }
        },
        swapComponent: function () {
            let newComponentId = (this.componentId != 1 ? 1 : 0);
            this.$emit('create-project', newComponentId);
        },
        emitEvent()
        {
            this.$emit('search-name', this.searchName);
            this.$emit('search-client', this.searchClient); 
        }
    },
    computed: {
        resetFilterHidden: function () {
            return !(this.searchName.length > 0 || this.searchClient.length > 0);
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
        color: rgb(13, 150, 196);
    }
</style>