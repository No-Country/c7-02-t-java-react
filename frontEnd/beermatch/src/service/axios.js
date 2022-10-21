// import { Router } from "next/router";
// import React from "react";
// import { toast } from "react-toastify";
// import { useRouter } from "next/router";


// export async function newBusiness(
//   userID,
//   token,
//   aboutUsText,
//   address,
//   city,
//   email,
//   image,
//   phone,
//   state,
//   urlFacebook,
//   urlInstagram
// ) {
//   const baseURL = "http://localhost:8080/business";
//   const router = useRouter();
//   await axios
//     .post(
//       baseURL,
//       {
//         aboutUsText: aboutUsText,
//         address: address,
//         city: city,
//         country: "Argentina",
//         email: email,
//         idUser: userID,
//         image: image,
//         name: name,
//         phone: phone,
//         state: state,
//         urlFacebook: urlFacebook,
//         urlInstagram: urlInstagram,
//       },
//       {
//         headers: {
//           Authorization: `Bearer ${token}`,
//         },
//       }
//     )
//     .then((response) => {
//       console.log(response.data);
//       toast.success("Negocio creado !", {
//         position: toast.POSITION.TOP_CENTER,
//       });
//       e.target.reset();
//       router.push("/dashboard/business/myBusiness");
//     })
//     .catch((error) => {
//       toast.error("Error de creación, pruebe nuevamente", {
//         position: toast.POSITION.TOP_CENTER,
//       });
//       console.log(error.message);
//     });
// }
