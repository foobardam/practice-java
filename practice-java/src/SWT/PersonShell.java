package SWT;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class PersonShell extends Shell {

	private DataBindingContext m_bindingContext;
	private Person person = new Person();
	private Scale ageScale;
	private Text firstNameText;
	private Text lastNameText;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		Display display = new Display();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					Display display = Display.getDefault();
					PersonShell shell = new PersonShell(display, SWT.SHELL_TRIM);
					shell.open();
					shell.layout();
					while (!shell.isDisposed()) {
						if (!display.readAndDispatch()) {
							display.sleep();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the shell.
	 * @param display
	 * @param style
	 */
	public PersonShell(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);
		setLayout(new GridLayout(2, false));

		new Label(this, SWT.NONE).setText("Age:");

		ageScale = new Scale(this, SWT.HORIZONTAL);
		ageScale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		new Label(this, SWT.NONE).setText("FirstName:");

		firstNameText = new Text(this, SWT.BORDER | SWT.SINGLE);
		firstNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("LastName:");

		lastNameText = new Text(this, SWT.BORDER | SWT.SINGLE);
		lastNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		if (person != null) {
			m_bindingContext = initDataBindings();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private DataBindingContext initDataBindings() {
		IObservableValue ageObserveWidget = SWTObservables
				.observeSelection(ageScale);
		IObservableValue ageObserveValue = BeansObservables.observeValue(
				person, "age");
		IObservableValue firstNameObserveWidget = SWTObservables.observeText(
				firstNameText, SWT.Modify);
		IObservableValue firstNameObserveValue = BeansObservables.observeValue(
				person, "firstName");
		IObservableValue lastNameObserveWidget = SWTObservables.observeText(
				lastNameText, SWT.Modify);
		IObservableValue lastNameObserveValue = BeansObservables.observeValue(
				person, "lastName");
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(ageObserveWidget, ageObserveValue, null, null);
		bindingContext.bindValue(firstNameObserveWidget, firstNameObserveValue,
				null, null);
		bindingContext.bindValue(lastNameObserveWidget, lastNameObserveValue,
				null, null);
		//
		return bindingContext;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson (Person newPerson) {
		setPerson(newPerson, true);
	}

	public void setPerson(Person newPerson, boolean update) {
		person = newPerson;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null;
			}
			if (person != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}

}
