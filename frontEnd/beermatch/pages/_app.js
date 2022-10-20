import Head from "next/head";
import Layout from "../src/layout/layout";
import ProtectedRoute from "../src/protectedRoutes/protectedroutes";
import "../styles/globals.css";

function MyApp({ Component, pageProps, router }) {
  if (router.pathname.startsWith("/dashboard/")) {
    return (
      <>
        <Head>
          <title>Beer Match</title>
          <meta
            name="Beer Match"
            content="Aplicación para mejorar la experiencia en bares y restaurantes con especialidad de cerveza"
          />
          <link rel="icon" href="/logo.png" />
        </Head>
        {/* <ProtectedRoute> */}
          <Layout>
            <Component {...pageProps} />
          </Layout>
        {/* </ProtectedRoute> */}
      </>
    );
  }
  return (
    <>
      <Head>
        <title>Beer Match</title>
        <meta
          name="Beer Match"
          content="Aplicación para mejorar la experiencia en bares y restaurantes con especialidad de cerveza"
        />
        <link rel="icon" href="/beer.png" />
      </Head>

      <Component {...pageProps} />
    </>
  );
}
export default MyApp;
