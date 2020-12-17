export { default as CreateProject } from '../..\\components\\CreateProject.vue'
export { default as Layout } from '../..\\components\\Layout.vue'
export { default as LoginForm } from '../..\\components\\LoginForm.vue'
export { default as Logo } from '../..\\components\\Logo.vue'
export { default as ProductCard } from '../..\\components\\ProductCard.vue'
export { default as ProjectCard } from '../..\\components\\ProjectCard.vue'
export { default as SearchBar } from '../..\\components\\SearchBar.vue'

export const LazyCreateProject = import('../..\\components\\CreateProject.vue' /* webpackChunkName: "components_CreateProject" */).then(c => c.default || c)
export const LazyLayout = import('../..\\components\\Layout.vue' /* webpackChunkName: "components_Layout" */).then(c => c.default || c)
export const LazyLoginForm = import('../..\\components\\LoginForm.vue' /* webpackChunkName: "components_LoginForm" */).then(c => c.default || c)
export const LazyLogo = import('../..\\components\\Logo.vue' /* webpackChunkName: "components_Logo" */).then(c => c.default || c)
export const LazyProductCard = import('../..\\components\\ProductCard.vue' /* webpackChunkName: "components_ProductCard" */).then(c => c.default || c)
export const LazyProjectCard = import('../..\\components\\ProjectCard.vue' /* webpackChunkName: "components_ProjectCard" */).then(c => c.default || c)
export const LazySearchBar = import('../..\\components\\SearchBar.vue' /* webpackChunkName: "components_SearchBar" */).then(c => c.default || c)
