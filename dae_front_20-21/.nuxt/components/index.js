export { default as CreateProject } from '../..\\components\\CreateProject.vue'
export { default as CreateStructure } from '../..\\components\\CreateStructure.vue'
export { default as ListProducts } from '../..\\components\\ListProducts.vue'
export { default as ProjectCard } from '../..\\components\\ProjectCard.vue'
export { default as SearchBar } from '../..\\components\\SearchBar.vue'

export const LazyCreateProject = import('../..\\components\\CreateProject.vue' /* webpackChunkName: "components_CreateProject" */).then(c => c.default || c)
export const LazyCreateStructure = import('../..\\components\\CreateStructure.vue' /* webpackChunkName: "components_CreateStructure" */).then(c => c.default || c)
export const LazyListProducts = import('../..\\components\\ListProducts.vue' /* webpackChunkName: "components_ListProducts" */).then(c => c.default || c)
export const LazyProjectCard = import('../..\\components\\ProjectCard.vue' /* webpackChunkName: "components_ProjectCard" */).then(c => c.default || c)
export const LazySearchBar = import('../..\\components\\SearchBar.vue' /* webpackChunkName: "components_SearchBar" */).then(c => c.default || c)
