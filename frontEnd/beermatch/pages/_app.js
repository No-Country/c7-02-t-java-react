import Head from "next/head";
import "../styles/globals.css";

function MyApp({ Component, pageProps, router }) {
  // if (router.pathname.startsWith("/dashboard/")) {
  //   return (
  //     <>
  //       <Head>
  //         <title>Create Next App</title>
  //         <meta
  //           name="Beer Match"
  //           content="Aplicacion apra mejorar la experiecia en bares y restaurantes con especialidad de cerveza"
  //         />
  //         <link rel="icon" href="/logo.png" />
  //       </Head>
  //         <Component {...pageProps} />
  //     </>
  //   );
  // }
  return (
    <>
      <Head>
        <title>Create Next App</title>
        <meta
          name="Beer Match"
          content="Aplicacion apra mejorar la experiecia en bares y restaurantes con especialidad de cerveza"
        />
        <link rel="icon" href="/logo.png" />
      </Head>

      <Component {...pageProps} />
    </>
  );
}
export default MyApp;
