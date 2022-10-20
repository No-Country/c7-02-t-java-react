import axios from "axios";
import React from "react";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRouter } from "next/router";
import Cookies from "js-cookie";

export default function NewBusiness() {
  axios;

  const [aboutUsText, setAboutUsText] = React.useState("");
  const [address, setAddress] = React.useState("");
  const [city, setCity] = React.useState("");
  // const [country, setCountry] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [image, setImage] = React.useState("");
  const [name, setName] = React.useState("");
  const [phone, setPhone] = React.useState("");
  const [state, setState] = React.useState("");
  const [urlFacebook, setUrlFacebook] = React.useState("");
  const [urlInstagram, setUrlInstagram] = React.useState("");

  const router = useRouter();

  const [userID, setUserID] = React.useState("");
  const [token, setToken] = React.useState("");

  React.useEffect(() => {
    const userID = Cookies.get("userID");
    setUserID(userID);
    const token =  Cookies.get("token");
    setToken(token);
  }, []);

  const baseURL = "http://localhost:8080/business";

  const handleNewBusiness = async (e) => {
    e.preventDefault();
    await axios
      .post(
        baseURL,
        {
          aboutUsText: aboutUsText,
          address: address,
          city: city,
          country: "Argentina",
          email: email,
          idUser: userID,
          image: image,
          name: name,
          phone: phone,
          state: state,
          urlFacebook: urlFacebook,
          urlInstagram: urlInstagram,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((response) => {
        console.log(response.data);
        toast.success("Negocio creado !", {
          position: toast.POSITION.TOP_CENTER,
        });
        e.target.reset();
        router.push("/dashboard/business/myBusiness");
      })
      .catch((error) => {
        toast.error("Error de creación, pruebe nuevamente", {
          position: toast.POSITION.TOP_CENTER,
        });
        console.log(error.message);
      });
  };

  console.log( {
    aboutUsText: aboutUsText,
    address: address,
    city: city,
    country: "Argentina",
    email: email,
    idUser: userID,
    image: image,
    name: name,
    phone: phone,
    state: state,
    urlFacebook: urlFacebook,
    urlInstagram: urlInstagram,
  },)

  const cleanState = () => {
    setAboutUsText("");
    setAddress("");
    setCity("");
    setEmail("");
    setImage("");
    setName("");
    setPhone("");
    setState("");
    setUrlFacebook("");
    setUrlInstagram("");
  };

  const inputOnInvalid = (e) => {
    e.target.setCustomValidity("Campo obligatorio");
  };

  const inputOnInput = (e) => {
    e.target.setCustomValidity("");
  };

  const style = {
    input:
      "pl-2 w-full font-light text-gray-800 bg-gray-50 outline-none border-none",
    inputContainer:
      "flex items-center border-2 bg-gray-50 border-gray-50 my-3 mx-2 p-0.5 w-96 px-3 rounded-xl outline outline-1 hover:outline-violet-500 hover:outline hover:outline-2 ",
  };

  return (
    <>
      <div className="m-4 lg:m-2 flex lg:justify-end justify-center ">
        <label
          htmlFor="my-modal"
          className="cursor-pointer rounded-xl uppercase modal-button bg-PurpleNavy text-white outline outline-2 hover:outline-2 py-2.5 px-3 hover:text-PurpleNavy hover:outline-MiddleYellow hover:bg-MiddleYellow"
        >
          Nuevo negocio
        </label>
        <input type="checkbox" id="my-modal" className="modal-toggle" />
        <div className="modal">
          <ToastContainer autoClose={2000} />
          <form onSubmit={handleNewBusiness}>
            <div className="modal-box bg-gray-50 w-full">
              <h3 className="font-bold text-lg text-PurpleNavy my-3 flex justify-center">
                Crea tu negocio
              </h3>
              <div className={style.inputContainer}>
                <input
                  id="name"
                  className={style.input}
                  type="text"
                  name="name"
                  placeholder="Nombre"
                  onChange={(e) => setName(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                />
              </div>
              <div className={style.inputContainer}>
                <input
                  id="address"
                  className={style.input}
                  type="text"
                  name="address"
                  placeholder="Dirección"
                  onChange={(e) => setAddress(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                />
              </div>
              <div className={style.inputContainer}>
                <input
                  id="state"
                  className={style.input}
                  type="text"
                  name="state"
                  placeholder="Provincia"
                  onChange={(e) => setState(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                />
              </div>
              <div className={style.inputContainer}>
                <input
                  id="city"
                  className={style.input}
                  type="text"
                  name="city"
                  placeholder="Ciudad"
                  onChange={(e) => setCity(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                />
              </div>
              <div className={style.inputContainer}>
                <input
                  id="phone"
                  className={style.input}
                  type="number"
                  name="phone"
                  placeholder="Telefono"
                  onChange={(e) => setPhone(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                />
              </div>
              <div className={style.inputContainer}>
                <input
                  id="email"
                  className={style.input}
                  type="text"
                  name="email"
                  placeholder="Email"
                  onChange={(e) => setEmail(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                />
              </div>
              <div className={style.inputContainer}>
                <input
                  id="urlFacebook"
                  className={style.input}
                  type="text"
                  name="urlFacebook"
                  placeholder="Facebook"
                  onChange={(e) => setUrlFacebook(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                />
              </div>
              <div className={style.inputContainer}>
                <input
                  id="urlInstagram"
                  className={style.input}
                  type="text"
                  name="urlInstagram"
                  placeholder="Instagram"
                  onChange={(e) => setUrlInstagram(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                />
              </div>
              <div className={style.inputContainer}>
                <label htmlFor="image">
                  <div className="p-2">
                    {image ? (
                      <h2 className={style.input}>&#128076; OK</h2>
                    ) : (
                      <h2 className={style.input}>
                        &#128193; Sube una imagen de tu negocio
                      </h2>
                    )}

                    <input
                      id="image"
                      className="hidden"
                      type="file"
                      name="image"
                      placeholder="Sobre nosotros"
                      onChangeCapture={(e) => setImage(e.target.value)}
                      onInvalid={inputOnInvalid}
                      onInput={inputOnInput}
                      required
                    />
                  </div>
                </label>
              </div>
              <div className={style.inputContainer}>
                <textarea
                  id="aboutUsText"
                  className={style.input}
                  rows="2"
                  type="text"
                  name="aboutUsText"
                  placeholder="Sobre nosotros"
                  onChange={(e) => setAboutUsText(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                ></textarea>
              </div>
              <div className="modal-action flex justify-between p-2">
                <label
                  htmlFor="my-modal"
                  className="btn btn-outline btn-error"
                  onClick={() => cleanState() }
                >
                  CERRAR
                </label>
                <label htmlFor="my-modal">
                  <button
                    type="submit"
                    htmlFor="my-modal"
                    className="btn btn-outline btn-info"
                  >
                    Guardar
                  </button>
                </label>
              </div>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}
