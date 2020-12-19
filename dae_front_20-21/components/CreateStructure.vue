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
                    v-model="geometria.numeroVaos"
                    clearable
                ></v-text-field>


                <v-text-field
                    solo
                    class="mb-0"
                    label="Comprimento do vão (m)"
                    clearable
                    v-model="geometria.comprimentoVao"
                ></v-text-field>

                <v-text-field
                    solo
                    class="mb-0"
                    label="Espaçamento entre vigas (m)"
                    v-model="geometria.espacamentoVigas"
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
                        v-model="selectedDadosPerfil"
                        label="Todas"
                        value="Todas"
                        ></v-checkbox>

                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selectedDadosPerfil"
                        label="Ω"
                        value="Ω"
                        ></v-checkbox>
                        
                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selectedDadosPerfil"
                        label="C"
                        value="C"
                        ></v-checkbox>
                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="selectedDadosPerfil"
                        label="Z"
                        value="Jacob"
                        ></v-checkbox>
                    </v-col>

                </v-row>


                <v-row>

                    <v-spacer></v-spacer>

                    <v-btn
                    class="mr-2"
                    depressed
                    @click="changeTab('forward')"
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
                        v-model="aplicacao.tipo"
                        label="Cobertura"
                        value="Cobertura"
                        ></v-checkbox>
                        
                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="aplicacao.tipo"
                        label="Fachada"
                        value="Fachada"
                        ></v-checkbox>
                    </v-col>

                    <v-col class="pt-0">
                        <v-checkbox
                        class="mt-0"
                        v-model="aplicacao.tipo"
                        label="Z"
                        value="Z"
                        ></v-checkbox>
                    </v-col>

                </v-row>

                <v-row>

                    <v-col class="py-0">

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Ângulo α"
                            v-model="aplicacao.angulo"
                            clearable
                        ></v-text-field>

                    </v-col>

                    <v-col class="py-0">

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Carga permanente (kN/m²)"
                            v-model="aplicacao.cargaPermanente"
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
                            v-model="aplicacao.sobrecarga"
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
                            v-model="aplicacao.neve"
                            clearable
                        ></v-text-field>

                    </v-col>

                </v-row>

                <v-row> 

                    <v-col class="py-0">

                        <span class="text-subtitle text-uppercase font-weight-bold px-3" style="padding-top:6px;">Altitude do Local:</span>
                        <v-checkbox
                        class="mt-0 px-2"
                        v-model="selectedAltitude"
                        label="≤ 1000m"
                        value="≤ 1000m"
                        ></v-checkbox>

                        <v-checkbox
                        class="mt-0 px-2"
                        v-model="selectedAltitude"
                        label="> 1000m"
                        value="> 1000m"
                        ></v-checkbox>

                    </v-col>
                        
                </v-row>

                <v-row>

                    <v-col>

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Vento - pressão (kN/m²)"
                            v-model="aplicacao.ventoPressao"
                            clearable
                        ></v-text-field>
                        
                    </v-col>

                    <v-col>

                        <v-text-field
                            solo
                            class="mb-0"
                            label="Vento - sucção (kN/m²)"
                            v-model="aplicacao.ventoSucao"
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
                        v-model="paramsContraventamentoTotal.contraventamentoTotal"
                        label="Contraventamento total"
                        value="Jacob"
                        ></v-checkbox>

                    </v-col>

                    <v-col class="pt-0">
                        <v-text-field
                            v-show="!paramsContraventamentoTotal.contraventamentoTotal"
                            solo
                            class="mb-0"
                            v-model="paramsContraventamentoTotal.verificacaoDeformacao"
                            label="Nº Contraventamentos Laterais"
                            clearable
                        ></v-text-field>
                    </v-col>

                </v-row>

                <v-row v-show="!paramsContraventamentoTotal.contraventamentoTotal">

                    <v-col class="pt-0">

                        <v-checkbox
                        class="mt-0 pt-3"
                        v-model="paramsContraventamentoTotal.contribuicaoChapaRevestimento"
                        label="Contribuição da chapa de revestimento"
                        value="Jacob"
                        ></v-checkbox>

                    </v-col>

                </v-row>

                <v-row>

                    <v-col class="pt-0">

                        <v-text-field
                            v-show="paramsContraventamentoTotal.contribuicaoChapaRevestimento"
                            solo
                            v-model="params.numeroFixacoes"
                            class="mb-0"
                            label="Nº de fixações"
                            clearable
                        ></v-text-field>

                    </v-col>

                    <v-col class="pt-0">

                        <v-text-field
                            v-show="paramsContraventamentoTotal.contribuicaoChapaRevestimento"
                            v-model="params.inerciaChapaRevestimento"
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
                @click="changeTab('backwards')"
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
                @click="changeTab('forward')"
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

                        <!-- <v-checkbox
                        class="mt-0 pt-3"
                        v-model="params.contraventamentoTotal"
                        label="Contraventamento total"
                        value="Jacob"
                        ></v-checkbox> -->

                    </v-col>

                    <v-col class="pt-0">
                        <!-- <v-text-field
                            v-show="!params.contraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Nº Contraventamentos Laterais"
                            clearable
                        ></v-text-field> -->
                    </v-col>

                </v-row>

                <v-row v-show="!params.contraventamentoTotal">

                    <v-col class="pt-0">

                        <!-- <v-checkbox
                        class="mt-0 pt-3"
                        v-model="params.contribuicaoChapaRevestimento"
                        label="Contribuição da chapa de revestimento"
                        value="Jacob"
                        ></v-checkbox> -->

                    </v-col>

                </v-row>

                <v-row>

                    <v-col class="pt-0">

                        <!-- <v-text-field
                            v-show="!params.contraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Nº de fixações"
                            clearable
                        ></v-text-field> -->

                    </v-col>

                    <v-col class="pt-0">

                        <!-- <v-text-field
                            v-show="!params.contraventamentoTotal"
                            solo
                            class="mb-0"
                            label="Inércia da Chapa de Revestimento (m4/m)"
                            clearable
                        ></v-text-field> -->

                    </v-col>

                </v-row>

            </v-card-text>

            <v-row class="pb-4 pr-5">
                
                <v-spacer></v-spacer>

                <v-btn
                class="mx-2"
                outlined
                depressed
                @click="changeTab('backwards')"
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
                 :disabled="index > 1"
                @click="postResultado"
                color="success"
                >
                    Finish
                    <v-icon>
                        mdi-check
                    </v-icon>
                </v-btn>
                
            </v-row>

        </v-card>

    </div>
</template>

<script>
export default {
    props:['title'],
    data: () => ({
        tabClicked: 0,
        index: 0,
        selectedAltitude: '',
        geometria:
        {
            numeroVaos: '',
            comprimentoVao: '',
            espacamentoVigas: ''
        },
        aplicacao:
        {
            tipo: '',
            angulo: '',
            sobrecarga: '',
            cargaPermanente: '',
            ventoSucao: '',
            ventoPressao: '',
            categoriaSobrecarga: '',
            neve: '',
        },
        //create parametros calculo se contraventamento total for true
        paramsContraventamentoTotal:
        {
            contraventamentoTotal: '',
            verificacaoDeformacao: '',
            limiteDeformacao:'',
        },
        //create parametros calculo se o contraventamento total for false e contribuicao chapa revestimento true
        params:
        {
            contraventamentoTotal: '',
            contribuicaoChapaRevestimento: '',
            numeroContraventamentosLaterais: '',
            numeroFixacoes: '',
            inerciaChapaRevestimento: '',
            verificacaoDeformacao: '',
            limiteDeformacao: ''
        },
        geometriaPosted: false,
        aplicacaoPosted: false,
        resultadoPosted: false,
        selectedDadosPerfil: '',
    }),
    methods:
    {
        changeTab: function (type)
        {
            if (this.tabClicked === 0 && !this.geometriaPosted)
                this.postGeometria();
            else if (this.tabClicked === 1 && !this.aplicacaoPosted)
                this.postAplicacao();
            else if (this.tabClicked === 2 && !this.resultadoPosted)
                this.postResultado();

            if (type !== 'backwards')
                this.tabClicked < 2 ? this.tabClicked++ : this.tabClicked--;
            else
                this.tabClicked > 0 && type === 'backwards' ? this.tabClicked-- : this.tabClicked++;
        },
        postGeometria: async function ()
        {
            await this.$axios.post('/api/geometria', this.geometria).then(response => this.geometriaPosted = true).catch(error => console.log(error.message));
        },
        postAplicacao: async function ()
        {
            await this.$axios.post('/api/aplicacao', this.aplicacao).then(response => this.aplicacaoPosted = true).catch(error => console.log(error.message));
        },
         postResultado: async function ()
        {
            //await this.$axios.post().then().catch();
        } 
    },
    watch: {
    tabClicked: function () {
        console.log(this.tabClicked);
    }
  },
}
</script>

<style scoped>

</style>