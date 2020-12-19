<template>
    <div style="width: 700px;">

        <h2 class="text-h4 py-4 font-weight-bold text-uppercase text-center">{{title}}</h2>

        <v-tabs fixed-tabs v-model="tabClicked">
            <v-tab >Geometria</v-tab>
            <v-tab >Cargas e Parâmetros de Cálculo</v-tab>
            <v-tab >Resultados</v-tab>
        </v-tabs>


        <!--  GEOMETRIA -->

        <v-card v-show="tabClicked === 0" flat class="px-16 pt-4 mb-6" color="#EEEEEE">
            <v-card-title>
                <h4 class="text-h6 font-weight-bold text-uppercase text-center">Dados Geométricos</h4>
            </v-card-title>

            <v-card-text>
                
                <v-text-field
                    class="mb-0"
                    full-width
                    solo
                    label="Número de vãos"
                    clearable
                ></v-text-field>


                <v-text-field
                    solo
                    class="mb-0"
                    labe="Comprimento do vão (m)"
                    clearable
                ></v-text-field>

                <v-text-field
                    solo
                    class="mb-0"
                    label="Espaçamento entre vigas (m)"
                    clearable
                ></v-text-field>

            </v-card-text>

        </v-card>

        <v-card v-show="tabClicked === 0" flat class="px-16 pt-4" color="#EEEEEE">
            <v-card-title>
                <h4 class="text-h6 font-weight-bold text-uppercase text-center">Dados do Perfil</h4>
            </v-card-title>

            <v-card-text>

                <v-row>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selected"
                        label="Todas"
                        value="Jacob"
                        ></v-checkbox>

                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selected"
                        label="Ω"
                        value="Jacob"
                        ></v-checkbox>
                        
                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selected"
                        label="C"
                        value="Jacob"
                        ></v-checkbox>
                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selected"
                        label="Z"
                        value="Jacob"
                        ></v-checkbox>
                    </v-col>

                </v-row>


                <v-row>

                    <v-spacer></v-spacer>

                    <v-btn
                    class="mx-2"
                    outlined
                    depressed
                    @click="tabClicked > 0 ? tabClicked-- : ''"
                    color="primary"
                    >
                        <v-icon>
                            mdi-chevron-left
                        </v-icon>
                        Passo Anterior
                    </v-btn>

                    <v-btn
                    class="mr-2"
                    depressed
                    @click="tabClicked < 2 ? tabClicked++ : ''"
                    color="primary"
                    >
                        Próximo Passo
                        <v-icon>
                            mdi-chevron-right
                        </v-icon>
                    </v-btn>
                    
                </v-row>

            </v-card-text>

        </v-card>

        <!--  Cargas e Parâmetros de Cálculo  -->

        <v-card v-show="tabClicked === 1" flat class="px-16 pt-4 mb-6" color="#EEEEEE">
            <v-card-title>
                <h4 class="text-h6 font-weight-bold text-uppercase text-center">Aplicação</h4>
            </v-card-title>

            <v-card-text>
                
                <v-row>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selected"
                        label="Cobertura"
                        value="Jacob"
                        ></v-checkbox>
                        
                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selected"
                        label="Fachada"
                        value="Jacob"
                        ></v-checkbox>
                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selected"
                        label="Z"
                        value="Jacob"
                        ></v-checkbox>
                    </v-col>

                </v-row>

                <v-row>

                    <v-col class="py-0">

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Ângulo α"
                            clearable
                        ></v-text-field>

                    </v-col>

                    <v-col class="py-0">

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Carga permanente (kN/m²)"
                            clearable
                        ></v-text-field>

                    </v-col>

                </v-row>

                <v-divider class="pb-6"></v-divider>

                <v-row>

                    <v-col class="py-0">

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Sobrecarga (kN/m²)"
                            clearable
                        ></v-text-field>

                    </v-col>

                        
                </v-row>

                <v-divider class="pb-6"></v-divider>

                <v-row>
                
                    <v-col class="py-0">

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Neve (kN/m²)"
                            clearable
                        ></v-text-field>

                    </v-col>

                </v-row>

                <v-row> 

                    <v-col class="py-0">

                        <span class="text-subtitle text-uppercase font-weight-bold px-3" style="padding-top:6px;">Altitude do Local:</span>
                        <v-checkbox
                        class="mt-0 px-2"
                        v-model="selected"
                        label="≤ 1000m"
                        value="Jacob"
                        ></v-checkbox>

                        <v-checkbox
                        class="mt-0 px-2"
                        v-model="selected"
                        label="> 1000m"
                        value="Jacob"
                        ></v-checkbox>

                    </v-col>
                        
                </v-row>

                <v-row>

                    <v-col>

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Vento - pressão (kN/m²)"
                            clearable
                        ></v-text-field>
                        
                    </v-col>

                    <v-col>

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Vento - sucção (kN/m²)"
                            clearable
                        ></v-text-field>
                        
                    </v-col>
                    
                </v-row>

            </v-card-text>

        </v-card>

        <v-card v-show="tabClicked === 1" flat class="px-16 pt-4 " color="#EEEEEE">
            <v-card-title>
                <h4 class="text-h6 font-weight-bold text-uppercase text-center">Parâmetros Adicionais</h4>
            </v-card-title>

            <v-card-text class="pb-0 mb-0">

                <v-row>

                    <v-col class="pt-0" cols="5">

                        <v-checkbox
                        class="mt-0 pt-3"
                        v-model="ContraventamentoTotal"
                        label="Contraventamento total"
                        value="Jacob"
                        ></v-checkbox>

                    </v-col>

                    <v-col class="pt-0">
                        <v-text-field
                            v-show="!ContraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Nº Contraventamentos Laterais"
                            clearable
                        ></v-text-field>
                    </v-col>

                </v-row>

                <v-row v-show="!ContraventamentoTotal">

                    <v-col class="pt-0">

                        <v-checkbox
                        class="mt-0 pt-3"
                        v-model="ContraventamentoChapaRevestimento"
                        label="Contribuição da chapa de revestimento"
                        value="Jacob"
                        ></v-checkbox>

                    </v-col>

                </v-row>

                <v-row>

                    <v-col class="pt-0">

                        <v-text-field
                            v-show="!ContraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Nº de fixações"
                            clearable
                        ></v-text-field>

                    </v-col>

                    <v-col class="pt-0">

                        <v-text-field
                            v-show="!ContraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Inércia da Chapa de Revestimento (m4/m)"
                            clearable
                        ></v-text-field>

                    </v-col>

                </v-row>

            </v-card-text>

            <v-row class="pb-4 pr-5">
                
                <v-spacer></v-spacer>

                <v-btn
                class="mx-2"
                outlined
                depressed
                @click="tabClicked > 0 ? tabClicked-- : ''"
                color="primary"
                >
                    <v-icon>
                        mdi-chevron-left
                    </v-icon>
                    Passo Anterior
                </v-btn>

                <v-btn
                class="mr-2"
                depressed
                @click="tabClicked < 2 ? tabClicked++ : ''"
                color="primary"
                >
                    Próximo Passo
                    <v-icon>
                        mdi-chevron-right
                    </v-icon>
                </v-btn>
                
            </v-row>

        </v-card>

        <!-- RESULTADOS -->

        <v-card v-show="tabClicked === 2" flat class="px-16 pt-4 " color="#EEEEEE">
            <v-card-title>
                <h4 class="text-h6 font-weight-bold text-uppercase text-center">Resultados</h4>
            </v-card-title>

            <v-card-text class="pb-0 mb-0">

                <v-row>

                    <v-col class="pt-0" cols="5">

                        <v-checkbox
                        class="mt-0 pt-3"
                        v-model="ContraventamentoTotal"
                        label="Contraventamento total"
                        value="Jacob"
                        ></v-checkbox>

                    </v-col>

                    <v-col class="pt-0">
                        <v-text-field
                            v-show="!ContraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Nº Contraventamentos Laterais"
                            clearable
                        ></v-text-field>
                    </v-col>

                </v-row>

                <v-row v-show="!ContraventamentoTotal">

                    <v-col class="pt-0">

                        <v-checkbox
                        class="mt-0 pt-3"
                        v-model="ContraventamentoChapaRevestimento"
                        label="Contribuição da chapa de revestimento"
                        value="Jacob"
                        ></v-checkbox>

                    </v-col>

                </v-row>

                <v-row>

                    <v-col class="pt-0">

                        <v-text-field
                            v-show="!ContraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Nº de fixações"
                            clearable
                        ></v-text-field>

                    </v-col>

                    <v-col class="pt-0">

                        <v-text-field
                            v-show="!ContraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Inércia da Chapa de Revestimento (m4/m)"
                            clearable
                        ></v-text-field>

                    </v-col>

                </v-row>

            </v-card-text>

            <v-row class="pb-4 pr-5">
                
                <v-spacer></v-spacer>

                <v-btn
                class="mx-2"
                outlined
                depressed
                @click="tabClicked > 0 ? tabClicked-- : ''"
                color="primary"
                >
                    <v-icon>
                        mdi-chevron-left
                    </v-icon>
                    Passo Anterior
                </v-btn>

                <v-btn
                class="mr-2"
                depressed
                @click="tabClicked < 2 ? tabClicked++ : ''"
                color="primary"
                >
                    Próximo Passo
                    <v-icon>
                        mdi-chevron-right
                    </v-icon>
                </v-btn>
                
            </v-row>

        </v-card>

    </div>
</template>

<script>
export default {
    props:['title'],
    methods: 
    {
        
    },
    data: () => ({
        tabClicked: 0,
        ContraventamentoTotal: false,
    }),
    
}
</script>

<style scoped>

</style>