<template>
    <v-row
        align="center"
        justify="center"
    >
        <v-card flat width="500" class="mx-auto mt-5">
            <v-card-title class="pa-4">
                <v-spacer></v-spacer>
                <h1 class="text-uppercase">Autenticação</h1>
                <v-spacer></v-spacer>
            </v-card-title>
            <v-divider class="mb-0"></v-divider>
            <v-card-text class="">
                <v-form>
                    <v-text-field
                        label="Username"
                        v-model.lazy="username"
                        :rules="usernameRules"
                        color="dark"
                        prepend-icon="mdi-account-circle"
                    />
                    <v-text-field
                        :type="showPassword ? 'text' : 'password'"
                        label="Password"
                        v-model.lazy="password"
                        prepend-icon="mdi-lock"
                        :rules="passwordRules"
                        color="dark"
                        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                        @click:append="showPassword = !showPassword"
                    />
                </v-form>
            </v-card-text>
            <v-card-actions>
                <v-btn block :disabled="username == '' || password == ''" depressed @click="login">Login</v-btn>
            </v-card-actions>
        </v-card>
    </v-row>
</template>

<script>
export default {
    data: () => ({
        showPassword: false,
        timeout: 3000,
        text: '',
        color: '',
        snackbar: false,
        username: 'projetista1',
        password: '123abc',
        usernameRules: [
            v => !!v || 'E-mail is required',
            v => (v && v.length >= 2) || 'Username is too short',
        ],
        passwordRules: [
            v => !!v || 'Password is required',
            v => (v && v.length >= 2) || 'Password is too short',
        ],
    }),
    methods: {
        async login(){
            localStorage.setItem('username', this.username);
            await this.$axios.post('/api/login/token/', {username: this.username, password: this.password})
            .then(response => this.setToken(response.data))
            .catch(error => console.log(error.message));
        },
        setToken(token)
        {
            localStorage.setItem('token', token);
            axios.defaults.headers.common = {'authorization': `Bearer ${token}`}
            this.$router.push('/project');
        }
    },
}
</script>

<style scoped>

</style>
