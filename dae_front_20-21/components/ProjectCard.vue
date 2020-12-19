<template>
    <v-hover v-slot="{ hover }">

        <v-card
            @click="viewMoreDetails"
            :elevation="hover ? 6 : 2"
            :class="{ 'on-hover': hover }"
            width="282px"
            color="#FAFAFA"
            height="120px"
        >

            <v-card-text>
                <div>
                    <p class="ma-0 pa-0 font-weight-bold text-center text-uppercase">
                        {{project.nome}}
                    </p>
                    <p class="ma-0 pa-0 font-weight-medium text-center text-caption">
                        <v-row>

                            <v-col class="pa-0 ma-0">

                                <b>Responsável</b>
                                <p>{{project.usernameProjetista}}</p>

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
er) {
    opacity: 0.6;
    }

    .show-btns {
    color: rgba(255, 255, 255, 1) !important;
    }
</style>r) {
    opacity: 0.6;
    }

    .show-btns {
    color: rgba(255, 255, 255, 1) !important;
    }
</style>r) {
    opacity: 0.6;
    }

    .show-btns {
    color: rgba(255, 255, 255, 1) !important;
    }
</style>) {
    opacity: 0.6;
    }

    .show-btns {
    color: rgba(255, 255, 255, 1) !important;
    }
</style>