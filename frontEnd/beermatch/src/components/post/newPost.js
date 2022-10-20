import axios from "axios";
import React from "react";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRouter } from "next/router";
import Cookies from "js-cookie";

export default function NewPost() {
  axios;

  const [attentionRate, setAttentionRate] = React.useState("");
  const [placeRate, setPlaceRate] = React.useState("");
  const [priceRate, setPriceRate] = React.useState("");
  const [qualityRate, setQualityRate] = React.useState("");
  const [text, setText] = React.useState("");
  const [businessID, setBusinessID] = React.useState("");

  const router = useRouter();

  const baseURL = `http://localhost:8080/review/${7}`;

  const [userID, setUserID] = React.useState("");
  const [token, setToken] = React.useState("");

  React.useEffect(() => {
    const userID = Cookies.get("userID");
    setUserID(userID);
    const token = Cookies.get("token");
    setToken(token);
  }, []);

  const handleNewBusiness = async (e) => {
    e.preventDefault();
    await axios
      .post(
        baseURL,
        {
          attentionRate: attentionRate,
          placeRate: placeRate,
          priceRate: priceRate,
          qualityRate: qualityRate,
          text: text,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((response) => {
        console.log(response.data);
        toast.success("Revisión creada !", {
          position: toast.POSITION.TOP_CENTER,
        });
        e.target.reset();
        router.push("/dashboard/posts");
      })
      .catch((error) => {
        toast.error("Error de creación, pruebe nuevamente", {
          position: toast.POSITION.TOP_CENTER,
        });
        console.log(error.message);
      });
  };

  console.log({
    attentionRate: attentionRate,
    placeRate: placeRate,
    priceRate: priceRate,
    qualityRate: qualityRate,
    text: text,
  });

  const cleanState = () => {
    setAttentionRate("");
    setBusinessID("");
    setPlaceRate("");
    setPriceRate("");
    setQualityRate("");
    setText("");
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
      "flex items-center border-2 bg-gray-50 border-gray-50 my-6 mx-2 p-0.5 w-96 px-3 rounded-xl outline outline-1 hover:outline-violet-500 hover:outline hover:outline-2 ",
  };

  return (
    <>
      <div className="m-4 lg:m-2 flex lg:justify-end justify-center ">
        <label
          htmlFor="my-modal"
          className="cursor-pointer rounded-xl uppercase modal-button bg-PurpleNavy text-white outline outline-2 hover:outline-2 py-2.5 px-3 hover:text-PurpleNavy hover:outline-MiddleYellow hover:bg-MiddleYellow"
        >
          Nuevo review
        </label>
        <input type="checkbox" id="my-modal" className="modal-toggle" />
        <div className="modal">
          <ToastContainer autoClose={2000} />
          <form onSubmit={handleNewBusiness}>
            <div className="modal-box bg-gray-50 w-full">
              <h3 className="font-bold text-lg text-purple-500 my-2 flex justify-center">
                Crea tu review
              </h3>
              <div className="flex flex-col space-y-2 p-2 my-2 w-full text-purple-500 ">
                <label
                  htmlFor="attentionRate"
                  className=" font-semibold text-sm mb-1 mt-2"
                >
                  Nota Atención
                </label>
                <input
                  type="range"
                  min="1"
                  max="5"
                  className="range range-sm"
                  step="1"
                  name="attentionRate"
                  id="attentionRate"
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  onChange={(e) => setAttentionRate(e.target.value)}
                  required
                />
                <div className="w-full flex justify-between text-sm px-2 text-purple-500">
                  <span>1</span>
                  <span>2</span>
                  <span>3</span>
                  <span>4</span>
                  <span>5</span>
                </div>
              </div>
              <div className="flex flex-col space-y-2 p-2 my-2 w-full text-purple-500 ">
                <label
                  htmlFor="placeRate"
                  className=" font-semibold text-sm mb-1 mt-2"
                >
                  Nota Lugar
                </label>
                <input
                  type="range"
                  min="1"
                  max="5"
                  className="range range-sm"
                  step="1"
                  name="placeRate"
                  id="placeRate"
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  onChange={(e) => setPlaceRate(e.target.value)}
                  required
                />
                <div className="w-full flex justify-between text-sm px-2 text-purple-500">
                  <span>1</span>
                  <span>2</span>
                  <span>3</span>
                  <span>4</span>
                  <span>5</span>
                </div>
              </div>

              <div className="flex flex-col space-y-2 p-2 my-2 w-full text-purple-500 ">
                <label
                  htmlFor="placeRate"
                  className=" font-semibold text-sm mb-1 mt-2"
                >
                  Nota Precio
                </label>
                <input
                  type="range"
                  min="1"
                  max="5"
                  className="range range-sm"
                  step="1"
                  name="priceRate"
                  id="priceRate"
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  onChange={(e) => setPriceRate(e.target.value)}
                  required
                />
                <div className="w-full flex justify-between text-sm px-2 text-purple-500">
                  <span>1</span>
                  <span>2</span>
                  <span>3</span>
                  <span>4</span>
                  <span>5</span>
                </div>
              </div>
              <div className="flex flex-col space-y-2 p-2 my-2 w-full text-purple-500 ">
                <label
                  htmlFor="placeRate"
                  className=" font-semibold text-sm mb-1 mt-2"
                >
                  Nota Calidad
                </label>
                <input
                  type="range"
                  min="1"
                  max="5"
                  className="range range-sm"
                  step="1"
                  name="qualityRate"
                  id="qualityRate"
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  onChange={(e) => setQualityRate(e.target.value)}
                  required
                />
                <div className="w-full flex justify-between text-sm px-2 text-purple-500">
                  <span>1</span>
                  <span>2</span>
                  <span>3</span>
                  <span>4</span>
                  <span>5</span>
                </div>
              </div>
              <div className={style.inputContainer}>
                <textarea
                  id="text"
                  className={style.input}
                  rows="2"
                  type="text"
                  name="text"
                  placeholder="Comentarios de review"
                  onChange={(e) => setText(e.target.value)}
                  onInvalid={inputOnInvalid}
                  onInput={inputOnInput}
                  required
                ></textarea>
              </div>
              <div className="modal-action flex justify-between p-2">
                <label
                  htmlFor="my-modal"
                  className="btn btn-outline btn-error"
                  onClick={() => cleanState()}
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
