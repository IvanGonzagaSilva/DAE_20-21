<template>
    <v-row>
        <v-col cols="1" class="mr-4">
            <v-btn
            class="m-2 py-6 mr-6"
            color="primary"
            @click="swapComponent()"
            >
            <v-icon dark>
                mdi-{{componentId == 0 ? "chevron-left" : (componentId == 2 ? "home" :  "plus")}}
            </v-icon>
            </v-btn>
        </v-col>
        <v-col >
            <v-text-field
            label="Solo"
            placeholder="Nome"
            solo
            :disabled="componentId !== 1"
            v-model="productName"
          ></v-text-field>
        </v-col>
        <v-col>
            <v-select
            :items="materialsArray"
            label="Materiais"
            solo
            :disabled="componentId !== 1"
            v-model="productMaterials"
            ></v-select>
        </v-col>
        <v-col>
            <v-select
            :items="dimensions"
            label="Dimensões"
            solo
            :disabled="componentId !== 1"
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
    props: ['materialsArray', 'componentId'],
    data: () => ({
      dimensions: ['200.00x1.80', '90.00x0.20', '50.00x1.00', '30.30x20.70'],
      productName: "",
      productMaterials: "",
      productDimensions: ""
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
        swapComponent: function () {
            let newComponentId = (this.componentId != 1 ? 1 : 0);
            this.$emit('create-project', newComponentId);
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