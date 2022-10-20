// import { createContext, useContext, useEffect, useState } from "react";

// export const authContext = createContext();

// export const useAuth = () => {
//   const context = useContext(authContext);
//   if (!context) throw new Error("not provider");
//   return context;
// };

// export function AuthProvider({ children }) {
//   const [user, setUser] = useState(null);
//   const [loading, setLoading] = useState(true);
//   const [userData, setUserData] = useState("");

//   const resetPassword = async (email) => sendPasswordResetEmail(auth, email);


//   const baseURL = "http://localhost:8080/auth/login";

//   const login = async (email, password) => {
   
//     await axios
//       .post(baseURL, {
//         email: email,
//         password: password,
//       })
//       .then((response) => {
//         console.log(response);
//         toast.success("Bienvenido cervecero !", {
//           position: toast.POSITION.TOP_CENTER,
//         });
//         localStorage.setItem("user", response.data.email);
//         localStorage.setItem("token", response.data.token);
//         localStorage.setItem("userID", response.data.id);
//         localStorage.setItem("rolUser", response.data.nameRol);
//         router.push("/dashboard/main");
//       })
//       .catch((error) => {
//         toast.error("Error de ingreso, pruebe nuevamente", {
//           position: toast.POSITION.TOP_CENTER,
//         });
//         console.log(error.message);
//       });
//   };

//   const logOut = async () => signOut(auth);

//   useEffect(() => {
//     const unsubsuscribe = onAuthStateChanged(auth, (currentUser) => {
//       setUser(currentUser);
//       setLoading(false);
//     });
//     return () => unsubsuscribe();
//   }, []);

//   const getDataUser = async () => {
//     const documentSnapshot = doc(db, "users", user.uid);
//     const docSnapshot = await getDoc(documentSnapshot);
//     console.log(docSnapshot.data());
//     console.log("invoked getDataUSER");
//     const userData = docSnapshot.data();
//     setUserData(userData);
//     localStorage.setItem("user", userData.uid);
//     localStorage.setItem("empresa", userData.empresa);
//   };

//   return (
//     <authContext.Provider
//       value={{
//         logIn,
//         user,
//         logOut,
//         loading,
//         resetPassword,
//         getDataUser,
//         userData,
//       }}
//     >
//       {children}
//     </authContext.Provider>
//   );
// }
