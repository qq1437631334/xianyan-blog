const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  typeList: state => state.global.typeList,
  adminInfo: state => state.user.adminInfo
}
export default getters
