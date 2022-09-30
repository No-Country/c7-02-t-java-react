import Head from "next/head";
import Layout from "../src/layout/layout";
import "../styles/globals.css";

function MyApp({ Component, pageProps, router }) {
  if (router.pathname.startsWith("/dashboard/")) {
    return (
      <>
        <Head>
          <title>Beer Match</title>
          <meta
            name="Beer Match"
            content="Aplicacion apra mejorar la experiecia en bares y restaurantes con especialidad de cerveza"
          />
          <link rel="icon" href="/logo.png" />
        </Head>
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </>
    );
  }
  return (
    <>
      <Head>
        <title>Beer Match</title>
        <meta
          name="Beer Match"
          content="Aplicacion apra mejorar la experiecia en bares y restaurantes con especialidad de cerveza"
        />
        <link rel="icon" href="/beer.png" />
      </Head>

      <Component {...pageProps} />
    </>
  );
}
export default MyApp;
