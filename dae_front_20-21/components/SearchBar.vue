<template>
    <div style="width: 1000px">

        <v-row class="px-3 pb-2">

            <v-col class="pl-0 pr-3">

                <v-btn
                class="m-2 py-6"
                color="primary"
                block
                @click="swapComponent((componentId == 2? 1 : 0))"
                >
                <v-icon dark class="pr-2">
                    mdi-{{(componentId == 0 || componentId == 2) ? "chevron-left": "folder-plus"}}
                </v-icon>
                    {{(componentId == 0 || componentId == 2) ? "Página Inicial": "Novo Projeto"}}
                </v-btn>

            </v-col>

            <v-col class="pr-0 pl-3">

                <v-btn
                class="m-2 py-6"
                color="primary"
                block
                @click="swapComponent(3)"
                >
                <v-icon dark class="pr-2">
                    mdi-{{componentId != 3 ? "folder-plus": "chevron-left"}}
                </v-icon>
                    {{componentId != 3 ? "Nova Estrutura": "Página Inicial"}}
                </v-btn>

            </v-col>

        </v-row>

        <v-row>

            <v-col class="py-0">
                <v-text-field
                label="Solo"
                placeholder="Nome"
                solo
                :disabled="componentId !== 1"
                v-model="searchName"
                @input="emitEvent"
            ></v-text-field>
            </v-col>

            <v-col class="py-0">
                <v-select
                :items="clientNameArray"
                label="Clientes"
                solo
                :disabled="componentId !== 1"
                v-model="searchClient"
                @input="emitEvent"
                ></v-select>
            </v-col>

            <v-col cols="2" class="px-0 py-0">
                <p v-show="!resetFilterHidden" class="text-uppercase text-body-1 reset-button font-weight-bold" color="blue" @click="resetFilters()">
                    Reset Filters
                </p>
            </v-col>

        </v-row>

    </div>
</template>

<script>
export default {
    props: ['clientNameArray', 'componentId'],
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
        swapComponent: function (id) {
            this.$emit('create-project', id);
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
        color: #2196F3;
    }
    .reset-button:hover
    {
        color: #1976D2;
    }
</style>
