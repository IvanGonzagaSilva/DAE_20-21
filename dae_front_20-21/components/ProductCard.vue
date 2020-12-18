<template>
    <v-hover v-slot="{ hover }">
    <v-card
        :elevation="hover ? 6 : 2"
        :class="{ 'on-hover': hover }"
        max-width="250px"
    >
        <v-img
        src="https://images-na.ssl-images-amazon.com/images/I/61eLaQcm5vL._SL1000_.jpg"
        height="200px"
        >
        <v-card-title class="title white--text">
            <v-row
            class="fill-height flex-column"
            justify="space-between"
            >
            <p class="text-center text-bottom">
                TITLE
            </p>

            </v-row>
        </v-card-title>
        </v-img>
        <v-card-text>
            <div>
                <p class="ma-0 body-1 font-weight-bold font-italic text-left">
                    TEXT
                </p>
                <p class="caption font-weight-medium font-italic text-left">
                    SUBTEXT
                </p>
            </div>
        </v-card-text>
    </v-card>
    </v-hover>
</template>

<script>
export default {
    data: () => ({
      icons: ['mdi-rewind', 'mdi-play', 'mdi-fast-forward'],
    })
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