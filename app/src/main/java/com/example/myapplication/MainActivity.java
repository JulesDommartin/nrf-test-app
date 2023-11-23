package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import no.nordicsemi.android.mesh.Group;
import no.nordicsemi.android.mesh.MeshManagerApi;
import no.nordicsemi.android.mesh.MeshManagerCallbacks;
import no.nordicsemi.android.mesh.MeshNetwork;
import no.nordicsemi.android.mesh.MeshProvisioningStatusCallbacks;
import no.nordicsemi.android.mesh.MeshStatusCallbacks;
import no.nordicsemi.android.mesh.provisionerstates.ProvisioningState;
import no.nordicsemi.android.mesh.provisionerstates.UnprovisionedMeshNode;
import no.nordicsemi.android.mesh.transport.ControlMessage;
import no.nordicsemi.android.mesh.transport.MeshMessage;
import no.nordicsemi.android.mesh.transport.ProvisionedMeshNode;

public class MainActivity extends AppCompatActivity implements
        MeshManagerCallbacks,
        MeshProvisioningStatusCallbacks,
        MeshStatusCallbacks {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    MeshManagerApi meshManagerApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });

        meshManagerApi = new MeshManagerApi(getApplicationContext());
        meshManagerApi.setMeshManagerCallbacks(this);
        meshManagerApi.setProvisioningStatusCallbacks(this);
        meshManagerApi.setMeshStatusCallbacks(this);
        meshManagerApi.loadMeshNetwork();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onNetworkLoaded(MeshNetwork meshNetwork) {
        createDefaultGroup();
        Log.v("TAG", "DB");
        // meshManagerApi.importMeshNetworkJson(meshManagerApi.exportMeshNetwork());
    }

    private void createDefaultGroup() {
        if (meshManagerApi.getMeshNetwork().getGroups().isEmpty()){
            Log.v("TAG", "Creating Default group");
            String name = "Default group";
            Group group = meshManagerApi.getMeshNetwork().createGroup(meshManagerApi.getMeshNetwork().getSelectedProvisioner(), name);
            if (group != null) {
                meshManagerApi.getMeshNetwork().addGroup(group);
            }
        }
    }

    @Override
    public void onNetworkUpdated(MeshNetwork meshNetwork) {

    }

    @Override
    public void onNetworkLoadFailed(String error) {

    }

    @Override
    public void onNetworkImported(MeshNetwork meshNetwork) {

    }

    @Override
    public void onNetworkImportFailed(String error) {

    }

    @Override
    public void sendProvisioningPdu(UnprovisionedMeshNode meshNode, byte[] pdu) {

    }

    @Override
    public void onMeshPduCreated(byte[] pdu) {

    }

    @Override
    public int getMtu() {
        return 0;
    }

    @Override
    public void onProvisioningStateChanged(UnprovisionedMeshNode meshNode, ProvisioningState.States state, @Nullable byte[] data) {

    }

    @Override
    public void onProvisioningFailed(UnprovisionedMeshNode meshNode, ProvisioningState.States state, byte[] data) {

    }

    @Override
    public void onProvisioningCompleted(ProvisionedMeshNode meshNode, ProvisioningState.States state, byte[] data) {

    }

    @Override
    public void onTransactionFailed(int dst, boolean hasIncompleteTimerExpired) {

    }

    @Override
    public void onUnknownPduReceived(int src, byte[] accessPayload) {

    }

    @Override
    public void onBlockAcknowledgementProcessed(int dst, @NonNull ControlMessage message) {

    }

    @Override
    public void onBlockAcknowledgementReceived(int src, @NonNull ControlMessage message) {

    }

    @Override
    public void onMeshMessageProcessed(int dst, @NonNull MeshMessage meshMessage) {

    }

    @Override
    public void onMeshMessageReceived(int src, @NonNull MeshMessage meshMessage) {

    }

    @Override
    public void onMessageDecryptionFailed(String meshLayer, String errorMessage) {

    }
}