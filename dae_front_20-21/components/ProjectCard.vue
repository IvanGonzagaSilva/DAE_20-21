<template>
    <v-hover v-slot="{ hover }">
        
    <v-card
        @click="viewMoreDetails"
        :elevation="hover ? 6 : 2"
        :class="{ 'on-hover': hover }"
        width="282px"
        height="210px"
    >
        <!--<v-img
        src="https://www.twmetals.com/media//catalog/category/pipe_bucket2.jpg"
        height="1px"
        >
        <v-card-title class="title white--text">
            <v-row
            class="fill-height flex-column"
            justify="space-between"
            >
            <p class="text-center text-bottom">

            </p>

            </v-row>
        </v-card-title>
        </v-img>-->
        <v-card-text>
            <div>
                <p class="ma-0 text-h5 font-weight-bold text-center text-uppercase">
                    {{project.nome}}
                </p>
                <p class="ma-0 font-weight-medium text-center">
                    <v-row>

                        <v-col class="pb-0">

                            <b>Cliente</b>: {{project.cliente.nome}}

                        </v-col>

                    </v-row>

                    <v-row>

                        <v-col  class="pb-0">
                            
                            <span v-for="(estrutura, index) in project.estruturas" :key="index">

                                <b>Estrutura</b>: {{estrutura.id}}
                                {{index > project.estruturas.length ? ',' : ''}}

                            </span>

                        </v-col>

                    </v-row>

                    <v-row>

                        <v-col  class="pb-0">

                            <b>Email</b>: {{project.cliente.email}}

                        </v-col>

                    </v-row>

                    <v-row>

                        <v-col  class="pb-0">

                            <b>Morada</b>: {{project.cliente.morada}}

                        </v-col>

                    </v-row>

                </p>
            </div>
        </v-card-text>
        
    </v-card>

    </v-hover>
</template>

<script>
export default {
    props: ["project"],
    data: () => ({
      icons: ['mdi-rewind', 'mdi-play', 'mdi-fast-forward'],
    }),
    methods: {
        viewMoreDetails: function ()
        {
            this.$emit('view-more-details', this.project);
        }
    }
}
</script>

<style scoped>
   .v-card {
        transition: opacity .4s ease-in-out;
        cursor: pointer;
    }

    .v-card:not(.on-hover) {
    opacity: 0.6;
    }

    .show-btns {
    color: rgba(255, 255, 255, 1) !important;
    }
</style>