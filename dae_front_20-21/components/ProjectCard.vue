<template>
    <v-hover v-slot="{ hover }">
        
    <v-card
        @click="viewMoreDetails"
        :elevation="hover ? 6 : 2"
        :class="{ 'on-hover': hover }"
        max-width="250px"
    >
        <v-img
        src="https://www.twmetals.com/media//catalog/category/pipe_bucket2.jpg"
        height="200px"
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
        </v-img>
        <v-card-text>
            <div>
                <p class="ma-0 body-1 font-weight-bold font-italic text-left">
                    {{project.nome}}
                </p>
                <p class="caption font-weight-medium font-italic text-left">
                    {{project}}
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