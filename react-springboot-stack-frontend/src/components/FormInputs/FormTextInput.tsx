import "./FormTextInput.css"

interface FormProps {
  name: string;
  type: string;
  placeholder: string;
  className: string;
}
const FormInput = (props:FormProps) => {
  return (
    <div>
      <input name={props.name} type={props.type} placeholder={props.placeholder} className={props.className} />
    </div>
  )
}

export default FormInput