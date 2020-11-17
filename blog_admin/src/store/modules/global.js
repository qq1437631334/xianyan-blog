const state = {
  typeList: [] // 分类列表
}

const mutations = {
  SET_TYPE_LIST: (state, typeList) => {
    state.typeList = typeList
  }
}

export default {
  namespaced: true,
  state,
  mutations
}

