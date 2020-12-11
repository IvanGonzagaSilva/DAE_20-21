<template>
  <v-row justify="center" align="center">

    <v-col cols="12" sm="8" md="6">

      <div class="text-center">
        
        <Layout />
      
      </div>

    </v-col>

  </v-row>
</template>

<script>
import Layout from '~/components/Layout.vue'

export default {
  components: {
    Layout,
  }
}
</script>

/*  Como Projetista, eu quero criar, consultar/listar/procurar, atualizar e remover
estruturas de determinado projeto, para que possa manter atualizada a lista de
estruturas de determinado projeto. Uma estrutura inclui, entre outros, um tipo de
material a utilizar (e.g., perfil, chapa), um nome e um conjunto de dimensões
geométricas e de parâmetros de cálculo adequados ao tipo de material a utilizar (ver
exemplo destes inputs em https://productlab.ofeliz.com/pt/simulator/1 para estruturas
com perfis). Uma estrutura contém também uma lista de materiais/produtos
selecionados para a sua construção. Estes materiais podem ter sido selecionados
diretamente pelo projetista do catálogo de materiais, ou selecionados de uma lista de
materiais sugerida por uma simulação (ver exemplo do resultado de uma simulação em
https://productlab.ofeliz.com/pt/simulator/1); */