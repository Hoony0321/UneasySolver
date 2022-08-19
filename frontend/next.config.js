/** @type {import('next').NextConfig} */
const nextConfig = {
  // reactStrictMode: true,
  reactStrictMode: false, // ! strictMode 켜져있으면 useEffect에서 first Rendering때 두번씩 실행되어서 막아놓음.
  swcMinify: true,
}

module.exports = nextConfig
